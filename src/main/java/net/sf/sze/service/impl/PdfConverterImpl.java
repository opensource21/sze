// PdfConverterImpl.java
//
// (c) SZE-Development-Team

/**
 *
 */
package net.sf.sze.service.impl;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfArray;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfDictionary;
import com.lowagie.text.pdf.PdfICCBased;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfString;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

import net.sf.sze.service.api.OO2PdfConverter;
import net.sf.sze.service.api.PdfConverter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.PrefixFileFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.awt.color.ColorSpace;
import java.awt.color.ICC_Profile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Arrays;

/**
 * A converter to convert oo-files to pdfs and A4-PDFs to A3-PDFs.
 * @author niels
 *
 */
@Service
public class PdfConverterImpl implements PdfConverter {

    /**
     * Log-Instanz.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            PdfConverterImpl.class);

    /**
     * Reader zum erstellen einer leeren Seite.
     */
    private PdfReader emptyPdfPage;

    /**
     * {@inheritDoc}
     */
    @Override
    public File concatAll(File directory, String praefix) {
        final String completePdfName = praefix + "_complete.pdf";
        final File completePdf = new File(directory, completePdfName);
        completePdf.delete();

        final String[] pdfs = directory.list(new PrefixFileFilter(praefix));
        if ((pdfs != null) && (pdfs.length > 0)) {

            final Document document = new Document();
            try {
                final PdfCopy copy = new PdfCopy(document, new FileOutputStream(
                        completePdf));
                copy.setPDFXConformance(PdfWriter.PDFA1B);
                document.open();
                addPdfAInfosToDictonary(copy);
                Arrays.sort(pdfs);

                for (String pdfName : pdfs) {
                    if (completePdfName.equals(pdfName)) {
                        continue;
                    }

                    try {
                        final PdfReader reader = new PdfReader(
                                new FileInputStream(new File(directory,
                                pdfName)));
                        for (int page = 1; page <= reader.getNumberOfPages();
                                page++) {
                            copy.addPage(copy.getImportedPage(reader, page));
                        }
                    } catch (DocumentException de) {
                        LOG.error(pdfName, de);
                        throw de;
                    } catch (IOException io) {
                        LOG.error(pdfName, io);
                        throw io;
                    }
                }
            } catch (DocumentException e) {
                throw new PDFConversionException(e);
            } catch (IOException e) {
                throw new PDFConversionException(e);
            } finally {
                document.close();
            }
        }

        return completePdf;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int convertOdtToA4(File odtFile, File pdfFileA4,
            OO2PdfConverter oo2pdfConverter) {
        LOG.debug("Create DinA4-PDF");

        final int result;
        try {
            File tempPdf = File.createTempFile(FilenameUtils.getBaseName(
                    pdfFileA4.getName()), ".pdf");
            FileUtils.deleteQuietly(tempPdf);
            oo2pdfConverter.convert(odtFile, tempPdf);

            PdfReader reader = new PdfReader(new FileInputStream(tempPdf));
            makeCleanPdfA(reader, pdfFileA4);
            FileUtils.deleteQuietly(tempPdf);
            result = reader.getNumberOfPages();
        } catch (DocumentException e) {
            throw new PDFConversionException(e);
        } catch (IOException e) {
            throw new PDFConversionException(e);
        }

        return result;
    }

    /**
     * Makes a clean PDF-A-File.
     * @param reader a pdfreader
     * @param pdfFileA4 the filename for the pdf-a-output.
     * @throws IOException io-trouble or file doesn't exist.
     * @throws DocumentException problems in itext.
     */
    private void makeCleanPdfA(PdfReader reader, File pdfFileA4)
            throws IOException, DocumentException {
        Document document = new Document();
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(pdfFileA4));
        copy.setPDFXConformance(PdfWriter.PDFA1B);
        document.open();
        addPdfAInfosToDictonary(copy);

        final int pageNrs = reader.getNumberOfPages();
        for (int page = 1; page <= pageNrs; page++) {
            copy.addPage(copy.getImportedPage(reader, page));
        }

        document.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void convertA4ToA3(File sourcePdfFileA4, File targetPdfFileA3,
            File targetPdfFileA4) {
        LOG.debug("Create DinA3-PDF");

        try {
            // we create a reader for a certain document
            PdfReader reader = new PdfReader(new FileInputStream(
                    sourcePdfFileA4));
            // we retrieve the total number of pages
            final int pageNrs = reader.getNumberOfPages();
            switch (pageNrs) {
            case 4:
                createA3Subdocument(reader, targetPdfFileA3, 4, 1, 2, 3);
                break;
            case 5:
                createA3Subdocument(reader, targetPdfFileA3, 0, 1, 2, 0);
                createA4Subdocument(reader, targetPdfFileA4, 3, 4);
                break;
            case 6:
                createA3Subdocument(reader, targetPdfFileA3, 6, 1, 2, 5);
                createA4Subdocument(reader, targetPdfFileA4, 3, 4);
                break;
            case 8:
                createA3Subdocument(reader, targetPdfFileA3, 8, 1, 2, 7, 6, 3,
                        4, 5);
                break;
            case 9:
                createA3Subdocument(reader, targetPdfFileA3, 0, 1, 2, 9, 8, 3,
                        4, 7);
                createA4Subdocument(reader, targetPdfFileA4, 5, 6);
                break;
            case 10:
                createA3Subdocument(reader, targetPdfFileA3, 10, 1, 2, 9, 8, 3,
                        4, 7);
                createA4Subdocument(reader, targetPdfFileA4, 5, 6);
                break;
            default:
                LOG.warn(sourcePdfFileA4.getAbsolutePath()
                        + " has not the right " + "number of pages " + reader
                        .getNumberOfPages() + ".");
            }
        } catch (DocumentException e) {
            throw new PDFConversionException(e);
        } catch (IOException e) {
            throw new PDFConversionException(e);
        }

    }

    /**
     * Orders the pages from an DIN-A4-document on a DIN-A3-document.
     * @param reader reader for DIN-A4-document
     * @param pdfFileA3 file in which the A3-document will be saved.
     * @param pages page-numbers in the order they will placed the paper in the
     * order left, right-side must be a multiple of 4. 0 is interpreted as an
     * empty-page.
     * @throws DocumentException problems in iText.
     * @throws IOException io-problems.
     */
    private void createA3Subdocument(PdfReader reader, File pdfFileA3,
            int... pages) throws DocumentException, IOException {
        if (pages.length % 4 != 0) {
            throw new IllegalArgumentException("The number of pages must be a "
                    + "multiple of 4.");
        }

        // we retrieve the size of the first page
        final Rectangle psize = reader.getPageSize(1);
        final float width = psize.getWidth();
        final float leftMargin = 0f;
        final float topMargin = 0f;

        // step 1: creation of a document-object
        final Document document = new Document(PageSize.A3.rotate());
        // step 2: we create a writer that listens to the document
        final PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(pdfFileA3));
        writer.setPDFXConformance(PdfWriter.PDFA1B);
        // step 3: we open the document
        document.open();
        addPdfAInfosToDictonary(writer);

        // step 4: we add content
        final PdfContentByte cb = writer.getDirectContent();
        final PdfTemplate[] pdfPages = new PdfTemplate[pages.length];
        for (int i = 0; i < pdfPages.length; i++) {
            final int pageNr = pages[i];
            final PdfTemplate page;
            if (pageNr == 0) {
                page = writer.getImportedPage(getEmptyPDFPage(psize), 1);
            } else {
                page = writer.getImportedPage(reader, pageNr);
            }

            if (i % 2 == 0) {
                document.newPage();
                cb.addTemplate(page, 1f, 0f, 0f, 1f, leftMargin, topMargin);
            } else {
                cb.addTemplate(page, 1f, 0f, 0f, 1f, width + leftMargin,
                        topMargin);
            }

        }

        writer.createXmpMetadata();
        // step 5: we close the document
        document.close();
    }

    /**
     * Create from a DIN-A4-dcoument a DIN-A4 subdocument.
     * @param reader reader for DIN-A4-document
     * @param pdfFileA4 file in which the new A4-document will be saved.
     * @param pages page-numbers in the order they will placed the paper.
     * @throws IOException
     * @throws DocumentException
     */
    private void createA4Subdocument(PdfReader reader, File pdfFileA4,
            int... pages) throws IOException, DocumentException {
        // step 1: creation of a document-object
        Document document = new Document(reader.getPageSize(1));
        // step 2: we create a writer that listens to the document
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(pdfFileA4));
        copy.setPDFXConformance(PdfWriter.PDFA1B);
        // step 3: we open the document
        document.open();
        addPdfAInfosToDictonary(copy);

        for (int pageNr : pages) {
            copy.addPage(copy.getImportedPage(reader, pageNr));
        }

        copy.createXmpMetadata();
        // step 5: we close the document
        document.close();
    }

    /**
     * Put PDF-A-Infos to the document.
     * @param writer the pdf-writer.
     * @throws IOException IOException.
     */
    private void addPdfAInfosToDictonary(PdfWriter writer) throws IOException {
        PdfDictionary outi = new PdfDictionary(PdfName.OUTPUTINTENT);
        outi.put(PdfName.OUTPUTCONDITIONIDENTIFIER, new PdfString(
                "sRGB IEC61966-2.1"));
        outi.put(PdfName.INFO, new PdfString("sRGB IEC61966-2.1"));
        outi.put(PdfName.S, PdfName.GTS_PDFA1);

        ICC_Profile icc = ICC_Profile.getInstance(ColorSpace.CS_sRGB);
        PdfICCBased ib = new PdfICCBased(icc);
        ib.remove(PdfName.ALTERNATE);
        outi.put(PdfName.DESTOUTPUTPROFILE, writer.addToBody(ib)
                .getIndirectReference());
        writer.getExtraCatalog().put(PdfName.OUTPUTINTENTS, new PdfArray(outi));
    }

    /**
     * Creates an document with an emptyPage.
     * @param pageSize the size of the Page.
     * @return a reader with 1 empty-page.
     */
    private synchronized PdfReader getEmptyPDFPage(Rectangle pageSize) {
        if (emptyPdfPage == null) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
                Document emptyDoc = new Document(pageSize);
                PdfWriter w = PdfWriter.getInstance(emptyDoc, baos);
                emptyDoc.open();
                w.getDirectContent().setLiteral(' ');
                emptyDoc.close();
                this.emptyPdfPage = new PdfReader(baos.toByteArray());
            } catch (Exception e) {
                LOG.error("Can't create an empty-page", e);
            }
        }

        return emptyPdfPage;
    }
}
