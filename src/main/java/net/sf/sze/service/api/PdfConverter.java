// PdfConverter.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
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
     * @throws PDFConversionException if there problems with the conversion.
     */
    File concatAll(File directory, String praefix)
            throws PDFConversionException;

    /**
     * Converts an Odt-document to a4-pdf.
     * @param odtFile the odt-file.
     * @param pdfFileA4 the name of the new pdf-file.
     * @param oo2pdfConverter converter which converts ODT to PDF-A.
     * @return the number of pages.
     * @throws PDFConversionException if there problems with the conversion.
     */
    int convertOdtToA4(File odtFile, File pdfFileA4,
            OO2PdfConverter oo2pdfConverter) throws PDFConversionException;

    /**
     * Converts the given A4 file into a A3 file,
     * with following rules:
     * <ul>
     *  <li>number of pages 1 create one din-a2-document,
     *  with an empty page as the end.</li>
     *  <li>number of pages 2 create one din-a2-document.</li>     *
     *  <li>number of pages = 3 create one din-a3-document,
     *  with an empty page as the end.</li>
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
     * @throws PDFConversionException if there problems with the conversion.
     */
    void convertA4ToA3(File sourcePdfFileA4, File targetPdfFileA3,
            File targetPdfFileA4) throws PDFConversionException;

    /**
     * {@link RuntimeException} in case of problems with the file-system
     * or an error in the iText-library @see {@link RuntimeException#getCause()}.
     *
     */
    public class PDFConversionException extends RuntimeException {

        /**
         * Initiates an object of type PdfConverter.PDFConversionException.
         * @param e DocumentException if the iText-library has a problem.
         */
        public PDFConversionException(DocumentException e) {
            super("Problem with iText", e);
        }

        /**
         * Initiates an object of type PdfConverter.PDFConversionException.
         * @param e IOException if there was IO-problems.
         */
        public PDFConversionException(IOException e) {
            super("Problem with file-io", e);
        }
    }
}
