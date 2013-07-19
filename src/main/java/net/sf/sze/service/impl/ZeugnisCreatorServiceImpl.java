package net.sf.sze.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateException;
import net.sf.jooreports.templates.ZippedDocumentTemplate;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.oo.SzeContentWrapper;
import net.sf.sze.service.api.OO2PdfConverter;
import net.sf.sze.service.api.PdfConverter;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Service der das eigentliche Erstellen der Zeugnisse übernimmt.
 */
public class ZeugnisCreatorServiceImpl implements InitializingBean, DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(ZeugnisCreatorServiceImpl.class);

    //TODO niels def config = ConfigurationHolder.config

    private final PdfConverter pdfConverter = new PdfConverterImpl();

    private OO2PdfConverter oo2pdfConverter;

    boolean createPdf = false;

    private File odtOutputBaseDir;
    private File pdfPrintOutputBaseDir;
    private File pdfScreenOutputBaseDir;



    @Override
    public void afterPropertiesSet() {
        log.info("PDF-Erstellung aktiv = ${config.createPDF}");
        if (config.createPDF) {
            createPdf = true;
        }
        if (createPdf) {
            switch (config.converter) {
            case 'SERVICE':
                assert config.ooExecutable, "'config.ooExecutable' muss gesetzt sein für die SERVICE-Variante."
                final File ooExecutable = new File(config.ooExecutable);
                assert ooExecutable.canExecute(), "${ooExecutable.getAbsolutePath()} kann nicht ausgeführt werden."
                int ooPort = config.ooPort?config.ooPort.toInteger():8100
                File userEnv = null;
                if (config.ooEnv) {
                    userEnv = new File("./ooenv")
                    if (!userEnv.exists()) {
                        assert userEnv.mkdirs();
                    }
                }
                oo2pdfConverter = new JodPdfConverter(ooExecutable, userEnv, ooPort);
                break;
            case 'UNO':
                oo2pdfConverter = new UnoPdfConverter();
                break;

            default:
                throw new IllegalArgumentException("Unknown converter ${config.converter}.")
            }
            oo2pdfConverter.init();
            log.info("Init PDF-Creation mit  ${config.converter}")
        }
        odtOutputBaseDir = new File(config.odtOutputDir);
        pdfPrintOutputBaseDir = new File(config.pdfPrintOutputDir);
        pdfScreenOutputBaseDir = new File(config.pdfScreenOutputDir);
    }

    @Override
    public void destroy() {
        oo2pdfConverter.closeConnection();
    }

    public ResultContainer createAllZeugnisse() {
        ResultContainer result = new ResultContainer();
        log.info("Erstelle alle Zeugnisse.")
        Set<Schulhalbjahr> halbjahre = new HashSet<Schulhalbjahr>();
        Set<Klasse> klassen = new HashSet<Klasse>();
        Schulhalbjahr.findAllBySelectable(true).each{halbjahr ->
            halbjahre.add(halbjahr);
            Zeugnis.findAllBySchulhalbjahr(halbjahr).each{ zeugnis ->
                try {
                    createZeugnis(zeugnis);
                    klassen.add(zeugnis.klasse);
                    result.addMessage("${zeugnis} wurde erstellt.");
                } catch (Exception e) {
                    log.error("Fehler beim drucken des Zeugnisses ${zeugnis}")
                }
            }
        }
        halbjahre.each {halbjahr ->
            klassen.each {klasse ->
                    createCompletePdfs(halbjahr, klasse)
                }
            };
        return result;
    }

    public File createZeugnisse(Schulhalbjahr halbjahr, Klasse klasse) {
        Zeugnis.findAllBySchulhalbjahrAndKlasse(halbjahr, klasse).
            each{ zeugnis ->
                createZeugnis(zeugnis);
        }
        return createCompletePdfs(halbjahr, klasse);
    }

    private File createCompletePdfs(Schulhalbjahr halbjahr, Klasse klasse){
        final String relativePath = createRelativePath(halbjahr, klasse);
        final File screenDir = new File(pdfScreenOutputBaseDir, relativePath);
        final File printDir = new File(pdfPrintOutputBaseDir, relativePath);

        pdfConverter.concatAll(printDir, "A3_");
        pdfConverter.concatAll(printDir, "A4_");
        return pdfConverter.concatAll(screenDir, "");
    }

    private String createRelativePath(Schulhalbjahr halbjahr, Klasse klasse) {
        final String klassenname =  klasse.calculateKlassenname(halbjahr.jahr);
        return "${halbjahr.createRelativePathName()}/Kl-${klassenname}"
    }

    public File createZeugnis(Zeugnis zeugnis) {
        final File result;
        final String relativePath = createRelativePath(zeugnis.schulhalbjahr, zeugnis.klasse);
        final Schueler schueler = zeugnis.schueler
        final String baseFileName = "${schueler.name}_${schueler.vorname}".replaceAll(" ","");
        final Schulhalbjahr halbjahr = zeugnis.schulhalbjahr
        File odtOutputDir = new File(odtOutputBaseDir, relativePath);
        File zeugnisOdtDatei = new File(odtOutputDir, "${baseFileName}.odt")
        prepareOutput(odtOutputDir, [zeugnisOdtDatei])
        File pdfScreenOutputDir = new File(pdfScreenOutputBaseDir, relativePath);
        File zeugnisPdfA4Datei = new File(pdfScreenOutputDir, "${baseFileName}.pdf")
        prepareOutput(pdfScreenOutputDir, [zeugnisPdfA4Datei])
        File pdfPrintOutputDir = new File(pdfPrintOutputBaseDir, relativePath);
        File zeugnisPdfPrintA3Datei = new File(pdfPrintOutputDir, "A3_${baseFileName}.pdf")
        File zeugnisPdfPrintA4Datei = new File(pdfPrintOutputDir, "A4_${baseFileName}.pdf")
        prepareOutput(pdfPrintOutputDir, [zeugnisPdfPrintA3Datei,
                zeugnisPdfPrintA4Datei])
        log.debug "Beginne Zeugnisdaten zusammenzustellen"
        Map zeugnisDaten = [:];
        zeugnisDaten['PLATZHALTER_LEER']=VariableUtility.PLATZHALTER_LEER
        def emptyMap=[:]
        Schulfach.findAll().each{
            zeugnisDaten["bw_${it.technicalName()}"] = VariableUtility.PLATZHALTER_LEER
            zeugnisDaten["bw_${it.technicalName()}_tg"] = ""
        }
        zeugnis.toPrintMap(zeugnisDaten)
        def noteAlsTextDarstellen =  zeugnis.zeugnisArt.noteAlsTextDarstellen
        (1..2).each {hj ->
            (1..2).each{ i ->
                zeugnisDaten["wp${hj}_${i}_name"] = VariableUtility.PLATZHALTER_LEER
                zeugnisDaten["wp${hj}_${i}_note"] = VariableUtility.PLATZHALTER_LEER
            }
        }
        if (zeugnis.schulhalbjahr.halbjahr == Halbjahr.Erstes_Halbjahr) {
            fillWPTabelle(zeugnis, zeugnisDaten, 1, noteAlsTextDarstellen)
        } else {
            def erstesSchulhalbjahr = Schulhalbjahr.findByJahrAndHalbjahr(
                    zeugnis.schulhalbjahr.jahr, Halbjahr.Erstes_Halbjahr)
            def zeugnisErstesHj = Zeugnis.findBySchuelerAndSchulhalbjahr(
                    zeugnis.schueler, erstesSchulhalbjahr)
            if (zeugnisErstesHj) {
                fillWPTabelle(zeugnisErstesHj, zeugnisDaten, 1, noteAlsTextDarstellen)
            }
            fillWPTabelle(zeugnis, zeugnisDaten, 2, noteAlsTextDarstellen)
        }

        fillHistoricalData(zeugnis, zeugnisDaten, noteAlsTextDarstellen)
        getSchulfachDetailInfo(zeugnis.formular, zeugnisDaten)
        //Hiernach hat man nur noch Strings
        removeNullAndAddBlank(zeugnisDaten)
        addNewlineAndBlocksatzVariables(zeugnisDaten)
        //Hinzufügen einiger Daten die man als Zahl oder Boolean braucht.
        zeugnisDaten['klassenstufe']=zeugnis.klasse.calculateKlassenstufe(halbjahr.jahr)
        zeugnisDaten['platzFuerSiegel']=zeugnis.zeugnisArt.platzFuerSiegel
        //createContentXML(zeugnisDaten)
        File templateFile = new File(config.templateDir, zeugnis.formular.templateFileName)
        createZeugnis(templateFile, zeugnisDaten, zeugnisOdtDatei)
        result = zeugnisOdtDatei;
        if (createPdf) {
            final File odtManualDir = new File (odtOutputDir, "bearbeitet");
            final File zeugnisOdtManualDatei = new File(odtManualDir, "${baseFileName}.odt")
            final File odtDatei;
            if (zeugnisOdtManualDatei.exists()) {
                odtDatei = zeugnisOdtManualDatei;
                log.info("Konvertiere korrigierte Datei " + odtDatei.getAbsolutePath());
            } else {
                odtDatei = zeugnisOdtDatei;
            }
            if (pdfConverter.convertOdtToA4(odtDatei, zeugnisPdfA4Datei, oo2pdfConverter)) {
                result = zeugnisPdfA4Datei
            }
            pdfConverter.convertA4ToA3(zeugnisPdfA4Datei, zeugnisPdfPrintA3Datei,
                    zeugnisPdfPrintA4Datei)
        }
        log.debug("Zeugnis erstellt.")
        return result
    }


    private fillWPTabelle(zeugnis, zeugnisDaten, hj, noteAlsTextDarstellen) {
        def i = 0;
        zeugnis.bewertungen.sort().each{Bewertung bewertung ->
            if (bewertung.relevant && bewertung.schulfach.typ == Schulfachtyp.WAHLPFLICHT) {
                i++;
                zeugnisDaten["wp${hj}_${i}_name"] = bewertung.schulfach.name
                zeugnisDaten["wp${hj}_${i}_note"] = bewertung.toPrintMap([:], noteAlsTextDarstellen)
            }
        }

    }

    private fillHistoricalData(Zeugnis zeugnis, Map zeugnisDaten, boolean noteAlsTextDarstellen) {
        //Fülle für alle möglichen Jahrgänge die Schulfächer (Wahlpflicht würde reichen, aber das spielt keine Rolle.)
        final Schueler schueler = zeugnis.schueler;
        final currentKlassenStufenIndex = zeugnis.calculateKlasssenstufenHalbjahresIndex();
        def emptyMap=[:]
        Schulfach.findAll().each{
            emptyMap["bw_${it.technicalName()}"] = ""
            emptyMap["bw_${it.technicalName()}_tg"] = ""
        }
        config.klassenStufen.each {stufe -> [1,2].each{hj->
                zeugnisDaten["shj${stufe}_${hj}"] = emptyMap
            }
        }

        //Historische Daten für Wahlpflicht
         Zeugnis.findAllBySchueler(schueler,[sort:'schulhalbjahr', order:'asc']).
                each{ oldZeugnis ->
                    def bewertungMap=emptyMap.clone()
                    oldZeugnis.bewertungen.each{bw -> bw.toPrintMap(bewertungMap, noteAlsTextDarstellen)}
                    Long klassenstufe = oldZeugnis.klasse.calculateKlassenstufe(oldZeugnis.schulhalbjahr.jahr)
                    int halbjahresId = oldZeugnis.schulhalbjahr.halbjahr.getId();
                    long oldKlassenstufenIndex = oldZeugnis.calculateKlasssenstufenHalbjahresIndex();
                    //Nur ältere und aktuelle Daten sollen eingefügt werden.
                    if (currentKlassenStufenIndex >= oldKlassenstufenIndex) {
                        zeugnisDaten["shj${klassenstufe}_${halbjahresId}"] = bewertungMap;
                    }
                }

    }

    private getSchulfachDetailInfo(zeugnisFormular, printMap) {
        Schulfach.list().each {schulfach ->
            printMap["${schulfach.technicalName()}_detailInfo"] = ""
        }
        zeugnisFormular.schulfachDetailInfos.each {detailInfo ->
            printMap["${detailInfo.schulfach.technicalName()}_detailInfo"] = detailInfo.detailInfo
        }
    }


    /**
     * Ersetzt Null durch Blank und fügt nach einem Text ein Leerzeichen ein.
     */
    private removeNullAndAddBlank(printMap) {
        printMap.each{key, value ->
            if (value == null) {
                printMap[key]=""
            } else if (value instanceof Map) {
                removeNullAndAddBlank(value)
            } else {
                if (printMap[key] && !printMap[key].toString().endsWith(" ")) {
                    printMap[key] = "${printMap[key]} ".toString()
                }
            }
        }
    }

    /**
     * Ergänzt für die direkt am Zeugnis hängenden Text spezielle Keys,<br>
     * nl - Wenn ein Eintrag nicht leer ist, wird ein Newline drangehängt<br>
     * bs - Newline wird durch TabNewline ersetzt.<br>
     * nlbs - Kombination aus bs und nl.
     * @param printMap
     */
    private addNewlineAndBlocksatzVariables(Map printMap) {
        Map nlValues = [:]
        Map bsValues = [:]
        Map nlbsValues = [:]
        printMap.each{key, value ->
            if (value instanceof String) {
                if (value) {
                    nlValues[key] = "${value}\n"
                } else {
                    nlValues[key] = "${value}"
                }
                //assert 'Erste Zeile\r\nZweite Zeile'.replaceAll(/\r?\n/, "\t\n") == 'Erste Zeile\t\nZweite Zeile'
                //assert 'Erste Zeile\nZweite Zeile'.replaceAll(/\r?\n/, "\t\n") == 'Erste Zeile\t\nZweite Zeile'
                bsValues[key] = value.replaceAll(/\r?\n/, "\t\n")
                nlbsValues[key] = nlValues[key].replaceAll(/\r?\n/, "\t\n")
            }
        }
        printMap['nl']=nlValues;
        printMap['bs']=bsValues;
        printMap['nlbs']=nlbsValues;
    }



    private prepareOutput(File outputDir, outputFiles) {
        if (!outputDir.exists()) {
            if (outputDir.mkdirs()) {
                log.info(outputDir.getAbsolutePath() + " angelegt.")
            } else {
                throw new FileNotFoundException(outputDir.getAbsolutePath() + " konnte nicht angelegt werden.");
            }
        }
        outputFiles.each {outputFile ->
            if (outputFile.exists()) {
                assert outputFile.delete(), "${outputFile.getAbsolutePath()} konnte nicht gelöscht werden"
            }
        }

    }

    public void createZeugnis(File templateFile, Map<String, Object> data,
            File odtFile) throws IOException, DocumentTemplateException {
        log.debug("Erstelle ODT-Datei")
        if (templateFile.isDirectory() || !templateFile.exists()) {
            throw new FileNotFoundException(templateFile.getAbsolutePath());
        }
        if (!"odt".equals(FilenameUtils.getExtension(odtFile.getName()))) {
            throw new IllegalArgumentException(odtFile.getName() +
                    " muss auf odt Enden.");
        }
        // InputStreams sind auch OK
        DocumentTemplate template = new ZippedDocumentTemplate(templateFile);
        try {
            template.setContentWrapper(new SzeContentWrapper());
            template.createDocument(data, new FileOutputStream(odtFile));
        } catch (DocumentTemplateException dtE) {
            logPrintMap(data, "")
            throw dtE;
        }
    }

    //------------- DEBUG_FUNCTIONS -------------------------------------------

    private logPrintMap(printMap, praefix) {
        printMap.each{key, value ->
            if (value instanceof Map) {
                logPrintMap(value, "${key}.")
            } else {
                log.info("${praefix}${key} -> ${value}")
            }
        }
    }

    private logPrintMap4Content(printMap, praefix, output) {
        printMap.each{key, value ->
            if (value instanceof Map) {
                logPrintMap4Content(value, "${key}.", output)
            } else {
                def variableName = "${praefix}${key}"
                def variablenDefinition = "<text:p text:style-name=\"P1\">${variableName}<text:tab/>\${${variableName}}<text:tab/><text:text-input text:description=\"${variableName}\">alias_${variableName}</text:text-input></text:p>"
                output << "${variablenDefinition}\n"
            }
        }
    }

    private createContentXML(zeugnisDaten) {
        File contentXml = new File("content.xml")
        contentXml << "xx"
        assert contentXml.delete()
        contentXml<<"""<?xml version="1.0" encoding="UTF-8"?>

        <office:document-content xmlns:office="urn:oasis:names:tc:opendocument:xmlns:office:1.0" xmlns:style="urn:oasis:names:tc:opendocument:xmlns:style:1.0" xmlns:text="urn:oasis:names:tc:opendocument:xmlns:text:1.0" xmlns:table="urn:oasis:names:tc:opendocument:xmlns:table:1.0" xmlns:draw="urn:oasis:names:tc:opendocument:xmlns:drawing:1.0" xmlns:fo="urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:meta="urn:oasis:names:tc:opendocument:xmlns:meta:1.0" xmlns:number="urn:oasis:names:tc:opendocument:xmlns:datastyle:1.0" xmlns:svg="urn:oasis:names:tc:opendocument:xmlns:svg-compatible:1.0" xmlns:chart="urn:oasis:names:tc:opendocument:xmlns:chart:1.0" xmlns:dr3d="urn:oasis:names:tc:opendocument:xmlns:dr3d:1.0" xmlns:math="http://www.w3.org/1998/Math/MathML" xmlns:form="urn:oasis:names:tc:opendocument:xmlns:form:1.0" xmlns:script="urn:oasis:names:tc:opendocument:xmlns:script:1.0" xmlns:ooo="http://openoffice.org/2004/office" xmlns:ooow="http://openoffice.org/2004/writer" xmlns:oooc="http://openoffice.org/2004/calc" xmlns:dom="http://www.w3.org/2001/xml-events" xmlns:xforms="http://www.w3.org/2002/xforms" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:field="urn:openoffice:names:experimental:ooxml-odf-interop:xmlns:field:1.0" office:version="1.1">
         <office:scripts/>
         <office:font-face-decls>
          <style:font-face style:name="Liberation Serif" svg:font-family="&apos;Liberation Serif&apos;" style:font-family-generic="roman" style:font-pitch="variable"/>
          <style:font-face style:name="Liberation Sans" svg:font-family="&apos;Liberation Sans&apos;" style:font-family-generic="swiss" style:font-pitch="variable"/>
          <style:font-face style:name="DejaVu Sans" svg:font-family="&apos;DejaVu Sans&apos;" style:font-family-generic="system" style:font-pitch="variable"/>
         </office:font-face-decls>
         <office:automatic-styles>
          <style:style style:name="P1" style:family="paragraph" style:parent-style-name="Standard">
           <style:paragraph-properties fo:padding-left="0cm" fo:padding-right="0cm" fo:padding-top="0.035cm" fo:padding-bottom="0cm" fo:border-left="none" fo:border-right="none" fo:border-top="0.018cm solid #000000" fo:border-bottom="none">
            <style:tab-stops>
             <style:tab-stop style:position="4.895cm"/>
             <style:tab-stop style:position="12.039cm"/>
            </style:tab-stops>
           </style:paragraph-properties>
           <style:text-properties fo:color="#800000" fo:font-size="12pt" style:font-size-asian="12pt" style:font-size-complex="12pt"/>
          </style:style>
         </office:automatic-styles>
         <office:body>
          <office:text>
           <text:sequence-decls>
            <text:sequence-decl text:display-outline-level="0" text:name="Illustration"/>
            <text:sequence-decl text:display-outline-level="0" text:name="Table"/>
            <text:sequence-decl text:display-outline-level="0" text:name="Text"/>
            <text:sequence-decl text:display-outline-level="0" text:name="Drawing"/>
           </text:sequence-decls>
"""
        logPrintMap4Content(zeugnisDaten, "", contentXml)
        contentXml<<"""  </office:text>
        </office:body>
        </office:document-content>
        """

    }
}
