// ZeugnisCreatorService.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import net.sf.jooreports.templates.DocumentTemplateException;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.service.api.PdfConverter.PDFConversionException;
import net.sf.sze.util.ResultContainer;

import java.io.File;
import java.io.IOException;

/**
 * Service zum Erstellen der Zeugnisse.
 *
 */
public interface ZeugnisCreatorService {

    /**
     * Erstellt alls Zeugnisse für selektierbare Schulhalbjahre.
     * @return Ein Container-Objekt mit Meldungen.
     * @throws PDFConversionException if there problems with the conversion.
     * @throws ODTConversionException if there problems with the template-engine.
     */
    ResultContainer createAllZeugnisse() throws ODTConversionException,
            PDFConversionException;

    /**
     * Erstellt die Zeugnisse fürs Schulhalbjahr und Klasse.
     * @param halbjahr das Schulhalbjahr.
     * @param klasse die Klasse.
     * @return die Datei mit den Zeugnissen.
     * @throws PDFConversionException if there problems with the conversion.
     * @throws ODTConversionException if there problems with the template-engine.
     */
    File createZeugnisse(Schulhalbjahr halbjahr, Klasse klasse)
            throws ODTConversionException, PDFConversionException;

    /**
     * Erstellt das Zeugnisdokument.
     * @param zeugnis die Zeugnisdaten.
     * @return Die Datei mit dem Ergebnis.
     * @throws PDFConversionException if there problems with the conversion.
     * @throws ODTConversionException if there problems with the template-engine.
     */
    File createZeugnis(Zeugnis zeugnis) throws ODTConversionException,
            PDFConversionException;

    /**
     * {@link RuntimeException} in case of problems with the file-system
     * or an error in the template-engine @see {@link RuntimeException#getCause()}.
     *
     */
    public class ODTConversionException extends RuntimeException {

        /**
         * Initiates an object.
         * @param e DocumentTemplateException in case of problems with the template-engine.
         */
        public ODTConversionException(DocumentTemplateException e) {
            super("Problem with the template-engine", e);
        }

        /**
         * Initiates an object.
         * @param e IOException if there was IO-problems.
         */
        public ODTConversionException(IOException e) {
            super("Problem with file-io", e);
        }

    }
}
