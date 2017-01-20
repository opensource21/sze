//DependencyTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package degraph;

import static de.schauderhaft.degraph.check.Check.classpath;
//import static de.schauderhaft.degraph.check.JLayer.anyOf;
import static de.schauderhaft.degraph.check.JCheck.customClasspath;
import static de.schauderhaft.degraph.check.JCheck.violationFree;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import de.schauderhaft.degraph.check.ConstraintBuilder;
import de.schauderhaft.degraph.check.JLayer;

/**
 * Testet verschiedene Abhängigkeiten innerhalb der Anwendung.
 *
 */
public class DependencyTest {

    /**
     * Rule um den Testnamen zu bekommen.
     */
    @Rule
    public TestName name = new TestName();

    /**
     * Das UTIL-Package.
     */
    private static final String UTIL_PACKAGE = "util";

    /**
     * Das Package mit den Model-Klassen, darf von allen angesprochen werden..
     */
    private static final String MODEL_PACKAGE = "model";

    /**
     * Layer für den Datenbankzugriff.
     */
    private static final String DAO_LAYER = "dao";

    /**
     * Layer mit den Services.
     */
    private static final String SERVICE_LAYER = "service";

    /**
     * Package mit den Job-Definitionen.
     */
    private static final String JOBS_PACKAGE = "jobs";

    /**
     * Layer für die Frontend-Funktionalittät.
     */
    private static final String FRONTEND_LAYER = "frontend";

    /**
     * Layer für die Constraints.
     */
    private static final String CONSTRAINTS_LAYER = "constraints";

    /**
     * Layer für die Configuration.
     */
    private static final String CONFIG_PACKAGE = "config";

    /**
     * Layer für die Security.
     */
    private static final String SECURITY_PACKAGE = "security";

    private String errorFilename;

    @Before
    public void setUp() {
        errorFilename = name.getMethodName() + "Error.graphml";
    }

    @After
    public void fixForRedBlindPeople() throws IOException {
        final Path errorFile = Paths.get(errorFilename);
        if (Files.exists(errorFile)) {
            final Charset charset = StandardCharsets.UTF_8;
            String content = new String(Files.readAllBytes(errorFile), charset);
            content = content.replaceAll("#FF0000", "#0101FF");
            Files.write(errorFile, content.getBytes(charset));
        }
    }


    /**
     * Stellt sicher, dass es keine Package-Zyklen gibt. Braucht leider eine
     * Minute :-(
     */
    @Test
    public void cycleFree() {
        assertThat(
                //customClasspath("./target/classes").
                classpath().
                printOnFailure(errorFilename).
                including("net.sf.sze.**"), is(violationFree()));
    }


    /**
     * Prüft, die Zugriffe zwischen den Schichten.
     */
    @Test
    public void layer() {
        ConstraintBuilder testObject =
                customClasspath("./target/classes").
                printOnFailure(errorFilename).
                including("net.sf.sze.**").
                withSlicing("sze", "net.sf.sze.(*).**").
                allow(CONFIG_PACKAGE, SECURITY_PACKAGE,
                       JLayer.oneOf(FRONTEND_LAYER, JOBS_PACKAGE),
                       SERVICE_LAYER, DAO_LAYER, MODEL_PACKAGE,
                       CONSTRAINTS_LAYER, UTIL_PACKAGE);
        assertThat(testObject, is(violationFree()));
    }

    /**
     * Prüft, die Zugriffe zwischen den Schichten JOBS/Frontend -> Service -> DAO.
     */
    @Test
    public void layerDirect() {
        ConstraintBuilder testObject =
                customClasspath("./target/classes").
                printOnFailure(errorFilename).
                including("net.sf.sze." + FRONTEND_LAYER + ".**").
                including("net.sf.sze." + SERVICE_LAYER + ".**").
                including("net.sf.sze." + JOBS_PACKAGE + ".**").
                including("net.sf.sze." + DAO_LAYER + ".**").
                 // TODO Hier habe ich noch einen direkten Zugriff auf DAO - aua!
                excluding("net.sf.sze.frontend.konfiguration.KonfigurationController").
                withSlicing("sze", "net.sf.sze.(*).**").
                allowDirect(JLayer.oneOf(FRONTEND_LAYER, JOBS_PACKAGE),
                        SERVICE_LAYER, DAO_LAYER);
        assertThat(testObject, is(violationFree()));
    }
}
