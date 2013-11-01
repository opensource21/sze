// OO2PdfConverterJodImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

/**
 *
 */
package net.sf.sze.service.impl;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import net.sf.sze.service.api.OO2PdfConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import java.net.ConnectException;

/**
 * Vorteil: Nutzt gute Klassenbibliothek. Classpath ist einfach zu händeln.
 * Nachteil: Der Service muss gestartet sein. Nicht ThreadSafe, daher auch synchronized.
 * @author niels
 *
 */
public class OO2PdfConverterJodImpl implements OO2PdfConverter {

    private final Logger log = LoggerFactory.getLogger(
            OO2PdfConverterJodImpl.class.getName());

    private OpenOfficeConnection connection;
    private DocumentConverter converter;
    private Process ooProcess;

    private final File ooCommandFile;
    private final int ooPort;
    private final String environmentUrl;
    private final String invisibleParam;

    /**
     * Initiates an object of type OO2PdfConverterJodImpl.
     * @param ooCommandFile Kommando um Openoffice zu starten.
     * @param userEnv die Umgebung mit der Konfiguration.
     */
    public OO2PdfConverterJodImpl(File ooCommandFile, File userEnv) {
        this(ooCommandFile, userEnv, 8100);
    }

    /**
     * Initiates an object of type OO2PdfConverterJodImpl.
     * @param ooCommandFile Kommando um Openoffice zu starten.
     * @param userEnv die Umgebung mit der Konfiguration.
     * @param ooPort der Port auf dem OpenOffice lauscht.
     */
    public OO2PdfConverterJodImpl(File ooCommandFile, File userEnv,
            int ooPort) {
        super();
        log.info("Creating OO2PdfConverterJodImpl with " + ooCommandFile
                .getAbsolutePath() + ":" + ooPort + " in " + userEnv);
        this.ooCommandFile = ooCommandFile;
        this.ooPort = ooPort;

        StringBuffer envUrl = null;
        if (userEnv != null) {
            try {
                envUrl = new StringBuffer(" -env:UserInstallation = file:///");
                envUrl.append(userEnv.getCanonicalPath().replace('\\', '/'));
            } catch (final IOException e) {
                log.error("Kann nicht die Umgebung setzen.", e);
            }
        }

        if (envUrl == null) {
            environmentUrl = "";
        } else {
            environmentUrl = envUrl.toString();
        }

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            log.info("Starting on windows");
            invisibleParam = "-quickstart";
        } else {
            log.info("Starting on " + System.getProperty("os.name"));
            invisibleParam = "-headless";
        }

    }

    @Override
    public synchronized void convert(File sourceFile, File outputFile) {
        log.debug("Erstelle DinA4-PDF");

        try {
            convertInternal(sourceFile, outputFile);
        } catch (final RuntimeException rE) {
            log.error("Fehler beim Konvertieren von " + sourceFile
                    .getAbsolutePath() + " -> " + outputFile.getAbsolutePath(),
                    rE);
            closeConnection();
            convertInternal(sourceFile, outputFile);
        }
    }

    private void convertInternal(File sourceFile, File outputFile) {
        ensureStartedOpenOfficeService(false);
        converter.convert(sourceFile, outputFile);
    }

    @Override
    public void init() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                closeConnection();
            }
        });
        ensureStartedOpenOfficeService(true);
    }

    @Override
    public void closeConnection() {
        log.info("Beende Verbindung zu OpenOffice");
        converter = null;

        try {
            if (connection != null) {
                if (connection.isConnected()) {
                    connection.disconnect();
                }

                connection = null;
                converter = null;
            }
        } finally {
            destroyOoProcess();
        }
    }

    private void ensureStartedOpenOfficeService(boolean init) {
        try {
            if ((connection == null) || !connection.isConnected()) {
                connection = new SocketOpenOfficeConnection("127.0.0.1",
                        ooPort);
                converter = new OpenOfficeDocumentConverter(connection);
                connection.connect();
            }
        } catch (final Exception e) {
            final ProcessBuilder processBuilder = new ProcessBuilder(
                    ooCommandFile.getAbsolutePath(), invisibleParam,
                    "-accept = socket,host =127.0.0.1,port =" + ooPort + ";urp;",
                    "-nofirststartwizard" + environmentUrl);
            try {
                closeConnection();
                log.info("Starte OO-Prozess. Init =" + init);
                ooProcess = processBuilder.start();
                // 10 Sekunden warten damit OO-Starten kann.
                Thread.sleep(10000);
            } catch (final IOException ioe) {
                throw new RuntimeException(ioe);
            } catch (final InterruptedException ie) {
                log.warn("Wait for beim Start von OpenOffice unterbrochen.",
                        ie);
            }

            connection = new SocketOpenOfficeConnection("127.0.0.1", ooPort);
            converter = new OpenOfficeDocumentConverter(connection);

            boolean connected = false;
            int nrOfRetries = 0;
            while (!connected && (nrOfRetries <= 12)) {
                nrOfRetries++;

                try {
                    connection.connect();
                    connected = true;
                } catch (final ConnectException ce) {
                    try {
                        Thread.sleep(10000);
                    } catch (final InterruptedException e1) {
                        // Ignore
                    }
                }
            }

            if (!connected) {
                log.error(
                        "Cant connect to OpenOffice. Is OpenOffice running on port "
                        + ooPort + ". Please start: soffice -headless -accept ="
                        + "\"socket,host =127.0.0.1,port =" + ooPort
                        + ";urp;\" -nofirststartwizard");

                if (!init) {
                    throw new IllegalStateException(
                            "Can't connect to openoffice!");
                }
            } else {
                log.info("Successful connected to OpenOffice.");
            }
        }
    }

    private void destroyOoProcess() {
        if (ooProcess != null) {
            log.info("Destroy OO-Prozess");
            ooProcess.destroy();
            ooProcess = null;
        }
    }
}
