// ZeugnisCreatorServiceImpl.java
//
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplate.ContentWrapper;
import net.sf.jooreports.templates.DocumentTemplateException;
import net.sf.jooreports.templates.ZippedDocumentTemplate;
import net.sf.sze.dao.api.zeugnis.SchulfachDao;
import net.sf.sze.dao.api.zeugnis.SchulhalbjahrDao;
import net.sf.sze.dao.api.zeugnis.ZeugnisDao;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.Halbjahr;
import net.sf.sze.model.zeugnis.Schulfach;
import net.sf.sze.model.zeugnis.SchulfachDetailInfo;
import net.sf.sze.model.zeugnis.Schulfachtyp;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.service.api.OO2PdfConverter;
import net.sf.sze.service.api.PdfConverter;
import net.sf.sze.service.api.ZeugnisCreatorService;
import net.sf.sze.util.ResultContainer;
import net.sf.sze.util.VariableUtility;

import org.apache.commons.io.FilenameUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

/**
 * Service der das eigentliche Erstellen der Zeugnisse übernimmt.
 */
@Service
@Transactional(readOnly = true)
public class ZeugnisCreatorServiceImpl implements InitializingBean,
        DisposableBean, ZeugnisCreatorService {

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
    private String odtOutputDir;

    @Value("${pdfPrintOutputDir}")
    private String pdfPrintOutputDir;

    @Value("${pdfScreenOutputDir}")
    private String pdfScreenOutputDir;

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

                    if (!userEnv.exists()) {
                        if (!userEnv.mkdirs()) {
                            throw new IllegalStateException(userEnv
                                    .getAbsolutePath()
                                    + " kann nicht angelegt werden.");
                        }
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

        odtOutputBaseDir = new File(odtOutputDir);
        pdfPrintOutputBaseDir = new File(pdfPrintOutputDir);
        pdfScreenOutputBaseDir = new File(pdfScreenOutputDir);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        oo2pdfConverter.destroy();
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

        final Map<String, Object> emptyMap = new HashMap<>();

        final Iterable<Schulfach> schulfaecher = schulfachDao.findAll();
        for (final Schulfach schulfach : schulfaecher) {
            zeugnisDaten.put("bw_" + schulfach.technicalName(), VariableUtility
                    .PLATZHALTER_LEER);
            zeugnisDaten.put("bw_" + schulfach.technicalName() + "_tg", "");
        }

        zeugnis.toPrintMap(zeugnisDaten);

        final boolean noteAlsTextDarstellen = zeugnis.getZeugnisArt()
                .getNoteAlsTextDarstellen().booleanValue();
        for (int hj = 1; hj < 3; hj++) {
            for (int i = 0; i < 3; i++) {
                zeugnisDaten.put("wp" + hj + "_" + i + "_name", VariableUtility
                        .PLATZHALTER_LEER);
                zeugnisDaten.put("wp" + hj + "_" + i + "_note", VariableUtility
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
                zeugnisDaten.put("wp" + hj + "_" + i + "_name", bewertung
                        .getSchulfach().getName());
                zeugnisDaten.put("wp" + hj + "_" + i + "_note", bewertung
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
            emptyMap.put("bw_" + schulfach.technicalName() + "", "");
            emptyMap.put("bw_" + schulfach.technicalName() + "_tg", "");
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
            printMap.put("" + schulfach.technicalName() + "_detailInfo", "");
        }

        for (final SchulfachDetailInfo detailInfo : zeugnisFormular
                .getSchulfachDetailInfos()) {
            printMap.put(detailInfo.getSchulfach().technicalName()
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
                if (value.toString().endsWith(" ")) {
                    printMap.put(key, value.toString() + " ");
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
                if (StringUtils.hasLength(value)) {
                    nlValues.put(key, "" + value + "\n");
                } else {
                    nlValues.put(key, value);
                }

                // assert "Erste Zeile\r\nZweite Zeile".replaceAll(/\r?\n/, "\t\n") == "Erste Zeile\t\nZweite Zeile"
                // assert "Erste Zeile\nZweite Zeile".replaceAll(/\r?\n/, "\t\n") == "Erste Zeile\t\nZweite Zeile"
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
            if (outputFile.exists()) {
                if (!outputFile.delete()) {
                    throw new IllegalStateException(outputFile
                            .getAbsolutePath()
                            + " konnte nicht gelöscht werden.");
                }
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

        try {
            if (templateFile.isDirectory() || !templateFile.exists()) {
                throw new FileNotFoundException(templateFile.getAbsolutePath());
            }

            if (!"odt".equals(FilenameUtils.getExtension(odtFile.getName()))) {
                throw new IllegalArgumentException(odtFile.getName()
                        + " muss auf odt Enden.");
            }

            // InputStreams sind auch OK
            final DocumentTemplate template = new ZippedDocumentTemplate(
                    templateFile);
            try {
                template.setContentWrapper(new SzeContentWrapper());
                template.createDocument(data, new FileOutputStream(odtFile));
            } catch (final DocumentTemplateException dtE) {
                logPrintMap(data, "");
                throw dtE;
            }
        } catch (final DocumentTemplateException e) {
            throw new ODTConversionException(e);
        } catch (final IOException e) {
            throw new ODTConversionException(e);
        }
    }

//  //------------- DEBUG_FUNCTIONS -------------------------------------------
//

    /**
     * Loggt die gesamte Daten-Map.
     * @param printMap die zu druckenden Daten.
     * @param praefix ein Präfix für die Rekursion.
     */
    @SuppressWarnings("unchecked")
    private void logPrintMap(Map<String, Object> printMap, String praefix) {
        for (final Map.Entry<String, Object> mapEntry : printMap.entrySet()) {
            final String key = mapEntry.getKey();
            final Object value = mapEntry.getValue();
            if (value instanceof Map) {
                logPrintMap((Map<String, Object>) value, key + ".");
            } else {
                LOG.info(praefix + key + " -> " + value);
            }
        }
    }
//
//  private logPrintMap4Content(printMap, praefix, output) {
//      printMap.each{key, value ->
//          if (value instanceof Map) {
//              logPrintMap4Content(value, "" +key}.", output)
//          } else {
//              def variableName = "" +praefix}" +key}"
//              def variablenDefinition = "<text:p text:style-name=\"P1\">" +variableName}<text:tab/>\" +${variableName}}<text:tab/><text:text-input text:description=\"${variableName}\">alias_${variableName}</text:text-input></text:p>"
//              output << "${variablenDefinition}\n"
//          }
//      }
//  }
//
//  private createContentXML(zeugnisDaten) {
//      File contentXml = new File("content.xml")
//      contentXml << "xx"
//      assert contentXml.delete()
//      contentXml<<"""<?xml version="1.0" encoding="UTF-8"?>
//
//      <office:document-content xmlns:office="urn:oasis:names:tc:opendocument:xmlns:office:1.0" xmlns:style="urn:oasis:names:tc:opendocument:xmlns:style:1.0" xmlns:text="urn:oasis:names:tc:opendocument:xmlns:text:1.0" xmlns:table="urn:oasis:names:tc:opendocument:xmlns:table:1.0" xmlns:draw="urn:oasis:names:tc:opendocument:xmlns:drawing:1.0" xmlns:fo="urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:meta="urn:oasis:names:tc:opendocument:xmlns:meta:1.0" xmlns:number="urn:oasis:names:tc:opendocument:xmlns:datastyle:1.0" xmlns:svg="urn:oasis:names:tc:opendocument:xmlns:svg-compatible:1.0" xmlns:chart="urn:oasis:names:tc:opendocument:xmlns:chart:1.0" xmlns:dr3d="urn:oasis:names:tc:opendocument:xmlns:dr3d:1.0" xmlns:math="http://www.w3.org/1998/Math/MathML" xmlns:form="urn:oasis:names:tc:opendocument:xmlns:form:1.0" xmlns:script="urn:oasis:names:tc:opendocument:xmlns:script:1.0" xmlns:ooo="http://openoffice.org/2004/office" xmlns:ooow="http://openoffice.org/2004/writer" xmlns:oooc="http://openoffice.org/2004/calc" xmlns:dom="http://www.w3.org/2001/xml-events" xmlns:xforms="http://www.w3.org/2002/xforms" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:field="urn:openoffice:names:experimental:ooxml-odf-interop:xmlns:field:1.0" office:version="1.1">
//       <office:scripts/>
//       <office:font-face-decls>
//        <style:font-face style:name="Liberation Serif" svg:font-family="&apos;Liberation Serif&apos;" style:font-family-generic="roman" style:font-pitch="variable"/>
//        <style:font-face style:name="Liberation Sans" svg:font-family="&apos;Liberation Sans&apos;" style:font-family-generic="swiss" style:font-pitch="variable"/>
//        <style:font-face style:name="DejaVu Sans" svg:font-family="&apos;DejaVu Sans&apos;" style:font-family-generic="system" style:font-pitch="variable"/>
//       </office:font-face-decls>
//       <office:automatic-styles>
//        <style:style style:name="P1" style:family="paragraph" style:parent-style-name="Standard">
//         <style:paragraph-properties fo:padding-left="0cm" fo:padding-right="0cm" fo:padding-top="0.035cm" fo:padding-bottom="0cm" fo:border-left="none" fo:border-right="none" fo:border-top="0.018cm solid #000000" fo:border-bottom="none">
//          <style:tab-stops>
//           <style:tab-stop style:position="4.895cm"/>
//           <style:tab-stop style:position="12.039cm"/>
//          </style:tab-stops>
//         </style:paragraph-properties>
//         <style:text-properties fo:color="#800000" fo:font-size="12pt" style:font-size-asian="12pt" style:font-size-complex="12pt"/>
//        </style:style>
//       </office:automatic-styles>
//       <office:body>
//        <office:text>
//         <text:sequence-decls>
//          <text:sequence-decl text:display-outline-level="0" text:name="Illustration"/>
//          <text:sequence-decl text:display-outline-level="0" text:name="Table"/>
//          <text:sequence-decl text:display-outline-level="0" text:name="Text"/>
//          <text:sequence-decl text:display-outline-level="0" text:name="Drawing"/>
//         </text:sequence-decls>
//"""
//      logPrintMap4Content(zeugnisDaten, "", contentXml)
//      contentXml<<"""  </office:text>
//      </office:body>
//      </office:document-content>
//      """
//
//  }

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
