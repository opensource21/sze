/**
 *
 */
package net.sf.sze.service.impl.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import junit.framework.TestCase;
import net.sf.sze.service.api.document.PdfConverter;

/**
 * Testet die Konvertierung der PDFs.
 * Der Test ist nicht ganz sauber, da man die Ergebnisse per Hand vergleichen muss.
 *
 */
//NICE die PDF sollten automatisch abgeglichen werden.
public class PdfConverterImplTest extends TestCase {

    /**
     * Verzeichnis mit Input-Dateien für die Tests.
     */
    private static final String PDF_INPUT = "src/test/resources/pdf/input/";

    /**
     * Verzeichnis mit den Referenz-Dokumenten.
     */
    private static final String REFERENZ_DIR = "src/test/resources/pdf/referenzen";

    private PdfConverter converter = new PdfConverterImpl();

    /**
     * Konkateniert verschiedene PDFs und prüft, ob es ein Ergebnis gibt.
     * @throws Exception wenn was schief geht.
     */
    //NICE eigentlich ist es nicht schön, dass man hier auf dem Referenzen-Dir arbeitet.
    //NICE es wäre gut die Seitenzahl abzutesten.
    public void testConcatAll() throws Exception {
        File result = converter.concatAll(new File(REFERENZ_DIR), "");
        assertNotNull(result);
        assertThat(result.getName()).startsWith("_");
        assertTrue(result.getAbsolutePath(), result.exists());
        result = converter.concatAll(new File(REFERENZ_DIR), "ResultA3_");
        assertNotNull(result);
        assertThat(result.getName()).startsWith("ResultA3__");
        assertTrue(result.getAbsolutePath(), result.exists());
        result = converter.concatAll(new File(REFERENZ_DIR), "ResultA4_");
        assertNotNull(result);
        assertThat(result.getName()).startsWith("ResultA4__");
        assertTrue(result.getAbsolutePath(), result.exists());
        result = converter.concatAll(new File(REFERENZ_DIR), "hslfd_");
        assertNotNull(result);
        assertFalse(result.getAbsolutePath(), result.exists());
    }

    /**
     * Konvertiert ein 4 Seitiges Dokument.
     * @throws Exception wenn was schief geht.
     */
    public void testConvertA4ToA3pages4() throws Exception {
        internalTestConvertA4ToA3(4);
    }

    /**
     * Konvertiert ein 5 Seitiges Dokument.
     * @throws Exception wenn was schief geht.
     */
    public void testConvertA4ToA3pages5() throws Exception {
        internalTestConvertA4ToA3(5);
    }

    /**
     * Konvertiert ein 6 Seitiges Dokument.
     * @throws Exception wenn was schief geht.
     */

    public void testConvertA4ToA3pages6() throws Exception {
        internalTestConvertA4ToA3(6);
    }

    /**
     * Konvertiert ein 8 Seitiges Dokument.
     * @throws Exception wenn was schief geht.
     */
    public void testConvertA4ToA3pages8() throws Exception {
        internalTestConvertA4ToA3(8);
    }

    /**
     * Konvertiert ein 9 Seitiges Dokument.
     * @throws Exception wenn was schief geht.
     */
    public void testConvertA4ToA3pages9() throws Exception {
        internalTestConvertA4ToA3(9);
    }

    /**
     * Konvertiert ein 10s Seitiges Dokument.
     * @throws Exception wenn was schief geht.
     */
    public void testConvertA4ToA3pages10() throws Exception {
        internalTestConvertA4ToA3(10);
    }

    private void internalTestConvertA4ToA3(int nrOfPages) throws Exception {
        final String sourceFileName = PDF_INPUT + "Beispiel" + nrOfPages + ".pdf";
        final String targetFileNameA3 = PDF_INPUT + "ResultA3_" + nrOfPages + ".pdf";
        final String targetFileNameA4 = PDF_INPUT + "ResultA4_" + nrOfPages + ".pdf";
        final File sourcePdfFileA4 = new File(sourceFileName);
        final File targetPdfFileA3 = new File(targetFileNameA3);
        final File targetPdfFileA4 = new File(targetFileNameA4);
        assertTrue(sourcePdfFileA4.getAbsolutePath() + "existiert. ",
                sourcePdfFileA4.exists());
        targetPdfFileA3.delete();
        targetPdfFileA4.delete();

        converter.convertA4ToA3(sourcePdfFileA4, targetPdfFileA3, targetPdfFileA4);
        assertEquals(nrOfPages % 4 != 0, targetPdfFileA4.exists());
        assertEquals(nrOfPages >= 4, targetPdfFileA3.exists());
        //System.out.println("You must check the result manually.");
    }
}
