// ZeugnisCreatorServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl.converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplate.ContentWrapper;
import net.sf.jooreports.templates.DocumentTemplateException;
import net.sf.jooreports.templates.DocumentTemplateFactory;
import net.sf.sze.dao.api.zeugnis.ZeugnisDao;
import net.sf.sze.dao.api.zeugnisconfig.SchulfachDao;
import net.sf.sze.dao.api.zeugnisconfig.SchulhalbjahrDao;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.SchulfachDetailInfo;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.model.zeugnisconfig.Halbjahr;
import net.sf.sze.model.zeugnisconfig.Schulfach;
import net.sf.sze.model.zeugnisconfig.Schulfachtyp;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;
import net.sf.sze.service.api.converter.OO2PdfConverter;
import net.sf.sze.service.api.converter.PdfConverter;
import net.sf.sze.service.api.converter.ZeugnisCreatorService;
import net.sf.sze.util.MapPrinter;
import net.sf.sze.util.ResultContainer;
import net.sf.sze.util.VariableUtility;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service der das eigentliche Erstellen der Zeugnisse übernimmt.
 */
@Service
@Transactional(readOnly = true)
public class ZeugnisCreatorServiceImpl implements InitializingBean,
        DisposableBean, ZeugnisCreatorService {

    /**
     * Praefix für historische WP-Einträge.
     */
    private static final String WP_PRAEFIX = "wp";

    /**
     * Die Log-Instanz.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            ZeugnisCreatorServiceImpl.class);

    @Value("${createPDF}")
    private boolean createPdf;

    @Value("${converter}")
    private String converter;

    @Value("${ooExecutable}")
    private String ooExecutableString;

    @Value("${ooPort}")
    private final int ooPort = 8100;

    @Value("${ooEnv}")
    private boolean ooEnv;

    @Value("${odtOutputDir}")
    private String odtOutputBaseDirAsString;

    @Value("${pdfPrintOutputDir}")
    private String pdfPrintOutputBaseDirAsString;

    @Value("${pdfScreenOutputDir}")
    private String pdfScreenOutputBaseDirAsString;

    @Value("${templateDir}")
    private String templateDir;

    /** Minimales Schuljahr. */
    @Value("${schuljahre.min}")
    private int minimalesSchuljahr;

    /** Maximales Schuljahr. */
    @Value("${schuljahre.max}")
    private int maximalesSchuljahr;

    /**
     * Ein PdfConverter.
     */
    @Resource
    private PdfConverter pdfConverter;

    private OO2PdfConverter oo2pdfConverter;

    private File odtOutputBaseDir;
    private File pdfPrintOutputBaseDir;
    private File pdfScreenOutputBaseDir;

    /**
     * Das {@link Zeugnis}-DAO.
     */
    @Resource
    private ZeugnisDao zeugnisDao;

    /**
     * Das {@link Schulhalbjahr}-DAO.
     */
    @Resource
    private SchulhalbjahrDao schulhalbjahrDao;

    /**
     * Das {@link Schulfach}-DAO.
     */
    @Resource
    private SchulfachDao schulfachDao;

    @Override
    public void afterPropertiesSet() {
        LOG.info("PDF-Erstellung aktiv = {}", Boolean.valueOf(createPdf));

        if (createPdf) {
            if ("SERVICE".equals(converter)) {
                final File ooExecutable = new File(ooExecutableString);
                if (!ooExecutable.canExecute()) {
                    throw new IllegalStateException(ooExecutable
                            .getAbsolutePath()
                            + "kann nicht ausgeführt werden.");
                }

                File userEnv = null;
                if (ooEnv) {
                    userEnv = new File("./ooenv");

                    if (!userEnv.exists() && !userEnv.mkdirs()) {
                        throw new IllegalStateException(userEnv
                                .getAbsolutePath()
                                + " kann nicht angelegt werden.");
                    }
                }

                oo2pdfConverter = new OO2PdfConverterJodImpl(ooExecutable,
                        userEnv, ooPort);
            } else if ("UNO".equals(converter)) {
                oo2pdfConverter = new OO2PdfConverterUnoImpl();
            } else {
                throw new IllegalArgumentException("Unknown converter "
                        + converter + ".");
            }

            oo2pdfConverter.init();
            LOG.info("Init PDF-Creation mit  {}", converter);
        }

        odtOutputBaseDir = new File(odtOutputBaseDirAsString);
        pdfPrintOutputBaseDir = new File(pdfPrintOutputBaseDirAsString);
        pdfScreenOutputBaseDir = new File(pdfScreenOutputBaseDirAsString);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        if (oo2pdfConverter != null) {
            oo2pdfConverter.closeConnection();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultContainer createAllZeugnisse() {
        final ResultContainer result = new ResultContainer();
        LOG.info("Erstelle alle Zeugnisse.");

        final Set<Klasse> klassen = new HashSet<Klasse>();
        final List<Schulhalbjahr> halbjahre = schulhalbjahrDao
                .findAllBySelectable(true);
        for (final Schulhalbjahr halbjahr : halbjahre) {
            final List<Zeugnis> zeugnisse = zeugnisDao.findAllBySchulhalbjahr(
                    halbjahr);
            for (final Zeugnis zeugnis : zeugnisse) {
                try {
                    createZeugnis(zeugnis);
                    klassen.add(zeugnis.getKlasse());
                    result.addMessage(zeugnis + " wurde erstellt.");
                } catch (final Exception e) {
                    LOG.error("Fehler beim drucken des Zeugnisses " + zeugnis);
                }
            }
        }

        for (final Schulhalbjahr halbjahr : halbjahre) {
            for (final Klasse klasse : klassen) {
                createCompletePdfs(halbjahr, klasse);
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File createZeugnisse(Schulhalbjahr halbjahr, Klasse klasse) {
        final List<Zeugnis> zeugnisse = zeugnisDao
                .findAllBySchulhalbjahrAndKlasse(halbjahr, klasse);
        for (final Zeugnis zeugnis : zeugnisse) {
            createZeugnis(zeugnis);
        }

        return createCompletePdfs(halbjahr, klasse);
    }

    /**
     * Erstellt die kompletten PDFs fürs Schulhalbjahr und Klasse.
     * @param halbjahr das Schulhalbjahr.
     * @param klasse die Klasse.
     * @return das komplette PDFs fürs Schuljahr und Klasse.
     */
    private File createCompletePdfs(Schulhalbjahr halbjahr, Klasse klasse) {
        final String relativePath = createRelativePath(halbjahr, klasse);
        final File screenDir = new File(pdfScreenOutputBaseDir, relativePath);
        final File printDir = new File(pdfPrintOutputBaseDir, relativePath);
        pdfConverter.concatAll(printDir, "A3_");
        pdfConverter.concatAll(printDir, "A4_");
        return pdfConverter.concatAll(screenDir, "");
    }

    /**
     * Erstellt den relativen Pfadnamen fürs Schulhalbjahr und Klasse.
     * @param halbjahr das Schulhalbjahr.
     * @param klasse die Klasse.
     * @return der relative Pfadname fürs Schulhalbjahr und Klasse.
     */
    private String createRelativePath(Schulhalbjahr halbjahr, Klasse klasse) {
        final String klassenname = klasse.calculateKlassenname(halbjahr
                .getJahr());
        return halbjahr.createRelativePathName() + "/Kl-" + klassenname;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File createZeugnis(Zeugnis zeugnis) {
        File result;
        final String relativePath = createRelativePath(zeugnis
                .getSchulhalbjahr(), zeugnis.getKlasse());
        final Schueler schueler = zeugnis.getSchueler();
        final String baseFileName = (schueler.getName() + "_" + schueler
                .getVorname()).replaceAll(" ", "");
        final Schulhalbjahr halbjahr = zeugnis.getSchulhalbjahr();
        final File odtOutputDir = new File(odtOutputBaseDir, relativePath);
        final File zeugnisOdtDatei = new File(odtOutputDir, baseFileName
                + ".odt");
        prepareOutput(odtOutputDir, zeugnisOdtDatei);

        final File pdfScreenOutputDir = new File(pdfScreenOutputBaseDir,
                relativePath);
        final File zeugnisPdfA4Datei = new File(pdfScreenOutputDir,
                baseFileName + ".pdf");
        prepareOutput(pdfScreenOutputDir, zeugnisPdfA4Datei);

        final File pdfPrintOutputDir = new File(pdfPrintOutputBaseDir,
                relativePath);
        final File zeugnisPdfPrintA3Datei = new File(pdfPrintOutputDir, "A3_"
                + baseFileName + ".pdf");
        final File zeugnisPdfPrintA4Datei = new File(pdfPrintOutputDir, "A4_"
                + baseFileName + ".pdf");
        prepareOutput(pdfPrintOutputDir, zeugnisPdfPrintA3Datei,
                zeugnisPdfPrintA4Datei);
        LOG.debug("Beginne Zeugnisdaten zusammenzustellen");

        final Map<String, Object> zeugnisDaten = new HashMap<>();
        zeugnisDaten.put("PLATZHALTER_LEER", VariableUtility.PLATZHALTER_LEER);

        final Iterable<Schulfach> schulfaecher = schulfachDao.findAll();
        for (final Schulfach schulfach : schulfaecher) {
            zeugnisDaten.put("bw_" + schulfach.getFormularKeyName(), VariableUtility
                    .PLATZHALTER_LEER);
            zeugnisDaten.put("bw_" + schulfach.getFormularKeyName() + "_tg", "");
        }

        zeugnis.toPrintMap(zeugnisDaten);

        final boolean noteAlsTextDarstellen = zeugnis.getZeugnisArt()
                .getNoteAlsTextDarstellen().booleanValue();
        for (int hj = 1; hj < 3; hj++) {
            for (int i = 1; i < 3; i++) {
                zeugnisDaten.put(WP_PRAEFIX + hj + "_" + i + "_name", VariableUtility
                        .PLATZHALTER_LEER);
                zeugnisDaten.put(WP_PRAEFIX + hj + "_" + i + "_note", VariableUtility
                        .PLATZHALTER_LEER);
            }
        }

        if (Halbjahr.Erstes_Halbjahr.equals(zeugnis.getSchulhalbjahr()
                .getHalbjahr())) {
            fillWPTabelle(zeugnis, zeugnisDaten, 1, noteAlsTextDarstellen);
        } else {
            final Schulhalbjahr erstesSchulhalbjahr = schulhalbjahrDao
                    .findByJahrAndHalbjahr(zeugnis.getSchulhalbjahr()
                    .getJahr(), Halbjahr.Erstes_Halbjahr);
            final Zeugnis zeugnisErstesHj = zeugnisDao
                    .findBySchuelerAndSchulhalbjahr(zeugnis.getSchueler(),
                    erstesSchulhalbjahr);
            if (zeugnisErstesHj != null) {
                fillWPTabelle(zeugnisErstesHj, zeugnisDaten, 1,
                        noteAlsTextDarstellen);
            }

            fillWPTabelle(zeugnis, zeugnisDaten, 2, noteAlsTextDarstellen);
        }

        fillHistoricalData(zeugnis, zeugnisDaten, noteAlsTextDarstellen);
        getSchulfachDetailInfo(zeugnis.getFormular(), zeugnisDaten);
        // Hiernach hat man nur noch Strings
        removeNullAndAddBlank(zeugnisDaten);
        addNewlineAndBlocksatzVariables(zeugnisDaten);
        // Hinzufügen einiger Daten die man als Zahl oder Boolean braucht.
        zeugnisDaten.put("klassenstufe", Integer.valueOf(zeugnis.getKlasse()
                .calculateKlassenstufe(halbjahr.getJahr())));
        zeugnisDaten.put("platzFuerSiegel", zeugnis.getZeugnisArt()
                .getPlatzFuerSiegel());

        // createContentXML(zeugnisDaten)
        final File templateFile = new File(templateDir, zeugnis.getFormular()
                .getTemplateFileName());
        createZeugnis(templateFile, zeugnisDaten, zeugnisOdtDatei);
        result = zeugnisOdtDatei;

        if (createPdf) {
            final File odtManualDir = new File(odtOutputDir, "bearbeitet");
            final File zeugnisOdtManualDatei = new File(odtManualDir,
                    baseFileName + ".odt");
            final File odtDatei;
            if (zeugnisOdtManualDatei.exists()) {
                odtDatei = zeugnisOdtManualDatei;
                LOG.info("Konvertiere korrigierte Datei " + odtDatei
                        .getAbsolutePath());
            } else {
                odtDatei = zeugnisOdtDatei;
            }

            if (pdfConverter.convertOdtToA4(odtDatei, zeugnisPdfA4Datei,
                    oo2pdfConverter) > 0) {
                result = zeugnisPdfA4Datei;
            }

            pdfConverter.convertA4ToA3(zeugnisPdfA4Datei,
                    zeugnisPdfPrintA3Datei, zeugnisPdfPrintA4Datei);
        }

        LOG.debug("Zeugnis erstellt.");
        return result;
    }

    private void fillWPTabelle(Zeugnis zeugnis, Map<String,
            Object> zeugnisDaten, int hj, boolean noteAlsTextDarstellen) {
        int i = 0;
        Collections.sort(zeugnis.getBewertungen());

        for (final Bewertung bewertung : zeugnis.getBewertungen()) {
            if (bewertung.getRelevant() && (bewertung.getSchulfach().getTyp()
                    == Schulfachtyp.WAHLPFLICHT)) {
                i++;
                zeugnisDaten.put(WP_PRAEFIX + hj + "_" + i + "_name", bewertung
                        .getSchulfach().getName());
                zeugnisDaten.put(WP_PRAEFIX + hj + "_" + i + "_note", bewertung
                        .createPrintText(noteAlsTextDarstellen));
            }
        }
    }

    private void fillHistoricalData(Zeugnis zeugnis, Map<String,
            Object> zeugnisDaten, boolean noteAlsTextDarstellen) {
        // Fülle für alle möglichen Jahrgänge die Schulfächer
        // (Wahlpflicht würde reichen, aber das spielt keine Rolle.)
        final Schueler schueler = zeugnis.getSchueler();
        final int currentKlassenStufenIndex = zeugnis
                .calculateKlasssenstufenHalbjahresIndex();
        final Map<String, Object> emptyMap = new HashMap<>();
        final Iterable<Schulfach> faecher = schulfachDao.findAll();
        for (final Schulfach schulfach : faecher) {
            emptyMap.put("bw_" + schulfach.getFormularKeyName() + "", "");
            emptyMap.put("bw_" + schulfach.getFormularKeyName() + "_tg", "");
        }

        for (int stufe = minimalesSchuljahr; stufe <= maximalesSchuljahr;
                stufe++) {
            for (int hj = 1; hj < 3; hj++) {
                zeugnisDaten.put("shj" + stufe + "_" + hj, emptyMap);
            }
        }

        final List<Zeugnis> oldZeugnisse = zeugnisDao
                .findAllBySchuelerOrderBySchulhalbjahrAsc(schueler);
        // Historische Daten für Wahlpflicht
        for (final Zeugnis oldZeugnis : oldZeugnisse) {
            final Map<String, Object> bewertungMap = new HashMap<>();
            bewertungMap.putAll(emptyMap);

            final List<Bewertung> bewertungen = oldZeugnis.getBewertungen();
            for (final Bewertung bw : bewertungen) {
                bw.toPrintMap(bewertungMap, noteAlsTextDarstellen);
            }

            final int klassenstufe = oldZeugnis.getKlasse()
                    .calculateKlassenstufe(oldZeugnis.getSchulhalbjahr()
                    .getJahr());
            final int halbjahresId = oldZeugnis.getSchulhalbjahr().getHalbjahr()
                    .getId();
            final long oldKlassenstufenIndex = oldZeugnis
                    .calculateKlasssenstufenHalbjahresIndex();
            // Nur ältere und aktuelle Daten sollen eingefügt werden.
            if (currentKlassenStufenIndex >= oldKlassenstufenIndex) {
                zeugnisDaten.put("shj" + klassenstufe + "_" + halbjahresId,
                        bewertungMap);
            }
        }

    }

    /**
     * ERgänzt die Schulfachdetailinfos.
     * @param zeugnisFormular das Formular.
     * @param printMap die zu füllenden Map.
     */
    private void getSchulfachDetailInfo(ZeugnisFormular zeugnisFormular,
            Map<String, Object> printMap) {
        final Iterable<Schulfach> faecher = schulfachDao.findAll();
        for (final Schulfach schulfach : faecher) {
            printMap.put("" + schulfach.getFormularKeyName() + "_detailInfo", "");
        }

        for (final SchulfachDetailInfo detailInfo : zeugnisFormular
                .getSchulfachDetailInfos()) {
            printMap.put(detailInfo.getSchulfach().getFormularKeyName()
                    + "_detailInfo", detailInfo.getDetailInfo());
        }
    }

    /**
     * Ersetzt Null durch Blank und fügt nach einem Text ein Leerzeichen ein.
     * @param printMap die zu verändernde Map.
     */
    @SuppressWarnings("unchecked")
    private void removeNullAndAddBlank(Map<String, Object> printMap) {
        for (final Map.Entry<String, Object> mapEntry : printMap.entrySet()) {
            final String key = mapEntry.getKey();
            final Object value = mapEntry.getValue();
            if (value == null) {
                printMap.put(key, "");
            } else if (value instanceof Map) {
                removeNullAndAddBlank((Map<String, Object>) value);
            } else {
                final String valueAsString = value.toString();
                if (valueAsString.length() > 0 && !valueAsString.endsWith(" ")) {
                    printMap.put(key, valueAsString + " ");
                }
            }
        }
    }

    /**
     * Ergänzt für die direkt am Zeugnis hängenden Text spezielle Keys,<br>
     * nl - Wenn ein Eintrag nicht leer ist, wird ein Newline drangehängt<br>
     * bs - Newline wird durch TabNewline ersetzt.<br>
     * nlbs - Kombination aus bs und nl.
     * @param printMap die bisherige PrintMap.
     */
    private void addNewlineAndBlocksatzVariables(Map<String, Object> printMap) {
        final Map<String, String> nlValues = new HashMap<>();
        final Map<String, String> bsValues = new HashMap<>();
        final Map<String, String> nlbsValues = new HashMap<>();
        for (final Map.Entry<String, Object> mapEntry : printMap.entrySet()) {
            final String key = mapEntry.getKey();

            if (mapEntry.getValue() instanceof String) {
                final String value = (String) mapEntry.getValue();
                if (StringUtils.isNotEmpty(value)) {
                    nlValues.put(key, "" + value + "\n");
                } else {
                    nlValues.put(key, value);
                }

                // assert "Erste Zeile\r\nZweite Zeile".replaceAll(/\r?\n/, "\t\n")
                // == "Erste Zeile\t\nZweite Zeile"
                // assert "Erste Zeile\nZweite Zeile".replaceAll(/\r?\n/, "\t\n")
                // == "Erste Zeile\t\nZweite Zeile"
                bsValues.put(key, value.replaceAll("\\r?\\n", "\t\n"));
                nlbsValues.put(key, nlValues.get(key).replaceAll("\\r?\\n",
                        "\t\n"));
            }
        }

        printMap.put("nl", nlValues);
        printMap.put("bs", bsValues);
        printMap.put("nlbs", nlbsValues);
    }

    /**
     * Stellt sicher, dass das Outputverzeichnis da ist und die angegebenen
     * Dateien gelöscht werden.
     * @param outputDir das Ausgabeverzeichnis.
     * @param outputFiles die dort zu löschenden Dateien.
     */
    private void prepareOutput(File outputDir, File... outputFiles) {
        if (!outputDir.exists()) {
            if (outputDir.mkdirs()) {
                LOG.info(outputDir.getAbsolutePath() + " angelegt.");
            } else {
                // NICE besseres Exception-Handling.
                throw new RuntimeException(new FileNotFoundException(outputDir
                        .getAbsolutePath() + " konnte nicht angelegt werden."));
            }
        }

        for (final File outputFile : outputFiles) {
            if (outputFile.exists() && !outputFile.delete()) {
                throw new IllegalStateException(outputFile
                        .getAbsolutePath()
                        + " konnte nicht gelöscht werden.");
            }
        }
    }

    /**
     * Erstellt die ODT-Datei.
     * @param templateFile das Template
     * @param data die Daten.
     * @param odtFile die Ausgabedatei.
     * @throws IOException
     * @throws DocumentTemplateException
     */
    public void createZeugnis(File templateFile, Map<String, Object> data,
            File odtFile) {
        LOG.debug("Erstelle ODT-Datei");
        if (LOG.isDebugEnabled()) {
            final MapPrinter mapPrinter = new MapPrinter(odtFile.getAbsolutePath()
                    + ".debug.txt", false);
            mapPrinter.print(data);
        }
        try {
            if (templateFile.isDirectory() || !templateFile.exists()) {
                throw new FileNotFoundException(templateFile.getAbsolutePath());
            }

            if (!"odt".equals(FilenameUtils.getExtension(odtFile.getName()))) {
                throw new IllegalArgumentException(odtFile.getName()
                        + " muss auf odt Enden.");
            }

            // InputStreams sind auch OK
            final DocumentTemplate template = new DocumentTemplateFactory()
                    .getTemplate(templateFile);
            //schaffe Abwärtskompatibilität, da JODREPORT 2.2.tab-patch ursrpünglich
            //genutzt wurde. Jetzt 2.4.0 wegen Maven. Siehe
            //http://sourceforge.net/apps/phpbb/jodreports/viewtopic.php?f=1&t=43
            @SuppressWarnings("unchecked")
            final Map<Object, Object> configurations = template.getConfigurations();
            configurations.put("process_jooscript_only", Boolean.FALSE);
            try {
                template.setContentWrapper(new SzeContentWrapper());
                template.createDocument(data, new FileOutputStream(odtFile));
            } catch (final DocumentTemplateException dtE) {
                final String fileName = odtFile.getAbsolutePath() + ".error.txt";
                final MapPrinter mapPrinter = new MapPrinter(fileName, false);
                mapPrinter.print(data);
                LOG.error("Fehler beim parsen des Dokuments. Die Map-Daten stehen in "
                        + fileName, dtE);
                throw dtE;
            }
        } catch (final DocumentTemplateException e) {
            throw new ODTConversionException(e);
        } catch (final IOException e) {
            throw new ODTConversionException(e);
        }
    }

    /**
     * Content Wrapper der auch \t durch Tab ersetzt.
     * @author niels
     *
     */
    private static final class SzeContentWrapper implements ContentWrapper {

        /**
         * {@inheritDoc}
         */
        @Override
        public String wrapContent(String content) {
            return "[#ftl]\n"
                    + "[#escape any as any?xml?replace(\"\\n\",\"<text:"
                    + "line-break />\")?replace(\"\\t\",\"<text:tab/>\")]\n"
                    + content + "[/#escape]";
        }
    }
}
