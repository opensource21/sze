// OO2PdfConverter.java
//
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import java.io.File;

public interface OO2PdfConverter {

    /**
     * Konvertiert ein OpenDocument-File nach PDF.
     * @param sourceFile ODT-Datei.
     * @param outputFile PDF-Datei.
     */
    void convert(File sourceFile, File outputFile);

    void init();

    void closeConnection();
}
