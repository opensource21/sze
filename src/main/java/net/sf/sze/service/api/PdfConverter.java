// PdfConverter.java
//
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import com.lowagie.text.DocumentException;

import java.io.File;
import java.io.IOException;

/**
 * Class PdfConverter.
 *
 */
public interface PdfConverter {

    /**
     * Concatinate all files in the directory with the given prefix.
     * @param directory the directory
     * @param praefix the filename-praefix
     * @return the complete file
     * @throws IOException if there was IO-problems.
     * @throws DocumentException if the iText-library has a problem.
     */
    File concatAll(File directory, String praefix) throws IOException,
            DocumentException;

    /**
     * Converts an Odt-document to a4-pdf.
     * @param odtFile the odt-file.
     * @param pdfFileA4 the name of the new pdf-file.
     * @param oo2pdfConverter converter which converts ODT to PDF-A.
     * @return the number of pages.
     * @throws IOException if there was IO-problems.
     * @throws DocumentException if the iText-library has a problem.
     */
    int convertOdtToA4(File odtFile, File pdfFileA4,
            OO2PdfConverter oo2pdfConverter) throws IOException,
            DocumentException;

    /**
     * Converts the given A4 file into a A3 file,
     * with following rules:
     * <ul>
     *  <li>number of pages < 4 nothing</li>
     *  <li>number of pages 4 create one din-a3-document.</li>
     *  <li>number of pages = 5 creates a din a3-document an one a4-pdf.
     *      Page 3 and 4 to Din-A4</li>
     *  <li>number of pages = 6 creates a din a3-document an one a4-pdf.
     *      Page 3 and 4 to Din-A4</li>
     *  <li>number of pages = 8 creates two din a3-document</li>
     *  <li>number of pages = 9 or 10 creates a din a3-document an one a4-pdf.
     *  Page 5 and 6 to Din-A4</li>
     * </ul>
     * @param sourcePdfFileA4 the source Din A4 file.
     * @param targetPdfFileA3 the target Din A3 file.
     * @param targetPdfFileA4 die Din A4-Datei für die Einlage.
     * @throws IOException if there was IO-problems.
     * @throws DocumentException if the iText-library has a problem.
     */
    void convertA4ToA3(File sourcePdfFileA4, File targetPdfFileA3,
            File targetPdfFileA4) throws DocumentException, IOException;
}
