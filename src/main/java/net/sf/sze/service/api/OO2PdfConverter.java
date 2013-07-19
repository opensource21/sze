package net.sf.sze.service.api;

import java.io.File;

public interface OO2PdfConverter {

    /**
     * Konvertiert ein OpenDocument-File nach PDF.
     * @param sourceFile ODT-Datei.
     * @param outputFile PDF-Datei.
     */
    public void convert(File sourceFile, File outputFile);
    
    public void init();
    
    public void closeConnection();

}