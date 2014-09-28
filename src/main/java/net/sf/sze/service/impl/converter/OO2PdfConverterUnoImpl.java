// OO2PdfConverterUnoImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

/**
 *
 */
package net.sf.sze.service.impl.converter;

import net.sf.sze.service.api.converter.OO2PdfConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.star.beans.PropertyValue;
import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XStorable;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.XCloseable;

import java.io.File;
import java.io.IOException;

/**
 * Vorteil: Man muss keine Service starten
 * Nachteil: Die Jars müssen von OpenOffice kommen.
 *
 * Converts an Document to PDF based on OpenOffice 2.4 via UNO-Schnittstelle.
 * Needs the following basic steps.
 * 1) Install OpenOffice 2.4 (I failed to compile in OpenOffice 3 coz they
 * have changed the jar files folder structure)
 *
 * 2) You need these 3 files in your classpath: unoil.jar, juh.jar, ridl.jar
 * You will find them in: "C:\Program Files\OpenOffice.org
 * 2.4\program\classes"
 * or /usr/share/java/openoffice on debian.
 *
 * (Note: Do NOT copy out these 3 files, you must linked them in the
 * original location, or else you will get runtime error for not able to
 * find the executable) I managed to converted RTF/Doc files to PDF using
 * OpenOffice, here my steps:
 *
 * @author niels
 *
 */
public class OO2PdfConverterUnoImpl implements OO2PdfConverter {

    /** The log. */
    private final Logger log = LoggerFactory.getLogger(
            OO2PdfConverterUnoImpl.class.getName());

    /**
     * Main-Methode.
     * @param args argumente.
     */
    public static void main(String[] args) {
        final File sourceFile = new File(
                "D:\\sandboxes\\schule_ppi\\sze\\oofiles\\templates\\fsn.odt");
        final File outputFile = new File(
                "D:\\sandboxes\\schule_ppi\\sze\\pdffiles\\fsn.pdf");
        final OO2PdfConverter unoC = new OO2PdfConverterUnoImpl();
        unoC.convert(sourceFile, outputFile);
        System.exit(0);
    }

    /*
     *  (non-Javadoc)
     * @see net.sf.sze.ootools.PdfConverter#convert(java.io.File, java.io.File)
     */
    @Override
    public void convert(File sourceFile, File outputFile) {
        try {
            // get the remote office component context
            XComponentContext xContext = Bootstrap.bootstrap();
            // get the remote office service manager
            XMultiComponentFactory xMCF = xContext.getServiceManager();
            Object oDesktop = xMCF.createInstanceWithContext(
                    "com.sun.star.frame.Desktop", xContext);
            XComponentLoader xCompLoader = (XComponentLoader) UnoRuntime
                    .queryInterface(XComponentLoader.class, oDesktop);

            log.info("OO ist initialisiert");

            StringBuffer sLoadUrl = new StringBuffer("file:///");
            sLoadUrl.append(sourceFile.getCanonicalPath().replace('\\', '/'));

            StringBuffer sSaveUrl = new StringBuffer("file:///");
            sSaveUrl.append(outputFile.getCanonicalPath().replace('\\', '/'));

            PropertyValue[] propertyValue = new PropertyValue[1];
            propertyValue[0] = new PropertyValue();
            propertyValue[0].Name = "Hidden";
            propertyValue[0].Value = Boolean.TRUE;

            Object oDocToStore = xCompLoader.loadComponentFromURL(sLoadUrl
                    .toString(), "_blank", 0, propertyValue);
            log.info("Dokument geladen.");

            XStorable xStorable = (XStorable) UnoRuntime.queryInterface(
                    XStorable.class, oDocToStore);

            propertyValue = new PropertyValue[2];
            propertyValue[0] = new PropertyValue();
            propertyValue[0].Name = "Overwrite";
            propertyValue[0].Value = Boolean.TRUE;
            propertyValue[1] = new PropertyValue();
            propertyValue[1].Name = "FilterName";
            propertyValue[1].Value = "writer_pdf_Export";
            xStorable.storeToURL(sSaveUrl.toString(), propertyValue);
            log.info("Dokument " + sSaveUrl + " gespeichert.");

            XCloseable xCloseable = (XCloseable) UnoRuntime.queryInterface(
                    XCloseable.class, oDocToStore);

            if (xCloseable != null) {
                xCloseable.close(false);
            } else {
                XComponent xComp = (XComponent) UnoRuntime.queryInterface(
                        XComponent.class, oDocToStore);
                xComp.dispose();
            }
        } catch (BootstrapException e) {
            String message =
                    "Die Remote-Komponenten konnte nicht kontaktiert werden.";
            log.warn(message, e);
            throw new ConvertionException(message, e);
        } catch (com.sun.star.uno.Exception e) {
            String message = "Konnte den Office-Service nicht starten.";
            log.warn(message, e);
            throw new ConvertionException(message, e);
        } catch (IOException e) {
            String message = "Canonicalname konnte nicht ermittelt werden";
            log.warn(message, e);
            throw new ConvertionException(message, e);
        }
    }

    /**
     * The convertion of the file was failed.
     * @author niels
     *
     */
    public static class ConvertionException extends RuntimeException {

        /** The Constant serialVersionUID. */
        private static final long serialVersionUID = 1L;

        /**
         * Instantiates a new convertion exception.
         *
         * @param message the message
         * @param cause the cause
         */
        public ConvertionException(String message, java.lang.Exception cause) {
            super(message, cause);
        }
    }


    @Override
    public void closeConnection() {
        // Nichts zu tun hier.
    }

    @Override
    public void init() {
        // Nichts zu tun hier.
    }
}
