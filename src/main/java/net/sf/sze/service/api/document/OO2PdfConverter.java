// OO2PdfConverter.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.document;

import java.io.File;

/**
 * Konverter um OpenDocument-Dateien nach PDF umzuwandeln.
 *
 */
public interface OO2PdfConverter {

    /**
     * Konvertiert ein OpenDocument-File nach PDF.
     * @param sourceFile ODT-Datei.
     * @param outputFile PDF-Datei.
     */
    void convert(File sourceFile, File outputFile);

    /**
     * Initialisierung.
     */
    void init();

    /**
     * Die Verbindung wieder schließen.
     */
    void closeConnection();
}
