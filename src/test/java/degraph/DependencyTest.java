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

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import scala.Function1;
import scala.runtime.AbstractFunction1;
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
     * Basispackage für alle Klassen.
     */
    private static final String SZE_BASE_PACKAGE = "net.sf.sze.";

    /**
     * Classpath für alle Produktiven-Klassen.
     */
    private static final String PRODUCTION_CLASSES = "./target/classes";

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

    /**
     * Filter als Scala-Funktion, welche alle Test-Klassen rausnimmt.
     */
    private static final Function1<String, Object> TEST_CLASS_FILTER =
            new AbstractFunction1<String, Object>() {
                @Override
                public Object apply(String directoryOrJarName) {
                    return Boolean.valueOf(!directoryOrJarName.contains("test-classes"));
                }
            };

    /**
     * Legt den Namen für die Fehlerdatei fest.
     */
    @Before
    public void setUp() {
        final boolean travis = Boolean.parseBoolean(System.getenv("TRAVIS"));
        final Package runtimePackage = Runtime.class.getPackage();
        final boolean oracleJDK = runtimePackage.getImplementationVendor().contains("Oracle");
        final boolean java7 = runtimePackage.getImplementationVersion().startsWith("1.7.0");
        // Oracle JDK 1.7 verursacht unter Travis ein OutOfMemory.
        Assume.assumeFalse(travis && oracleJDK && java7);
        errorFilename = name.getMethodName() + "Error.graphml";
    }

    /**
     * Ändert die Farbe ROT in BLAU.
     * @throws IOException wenn die Datei nicht gelesen oder geschrieben werden kann.
     */
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
                classpath().noJars().filterClasspath(TEST_CLASS_FILTER).
                printOnFailure(errorFilename).
                including(SZE_BASE_PACKAGE + "**"), is(violationFree()));
    }


    /**
     * Prüft, die Zugriffe zwischen den Schichten.
     */
    @Test
    public void layer() {
        ConstraintBuilder testObject =
                customClasspath(PRODUCTION_CLASSES).
                printOnFailure(errorFilename).
                including(SZE_BASE_PACKAGE + "**").
                withSlicing("sze", SZE_BASE_PACKAGE + "(*).**").
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
                customClasspath(PRODUCTION_CLASSES).
                printOnFailure(errorFilename).
                including(SZE_BASE_PACKAGE + FRONTEND_LAYER + ".**").
                including(SZE_BASE_PACKAGE + SERVICE_LAYER + ".**").
                including(SZE_BASE_PACKAGE + JOBS_PACKAGE + ".**").
                including(SZE_BASE_PACKAGE + DAO_LAYER + ".**").
                withSlicing("sze", SZE_BASE_PACKAGE + "(*).**").
                allowDirect(JLayer.oneOf(FRONTEND_LAYER, JOBS_PACKAGE),
                        SERVICE_LAYER, DAO_LAYER);
        assertThat(testObject, is(violationFree()));
    }

    /**
     * Prüft, die Zugriffe zwischen den fachlichen Schichten.
     */
    @Test
    public void functionalSlices() {
        ConstraintBuilder testObject =
                customClasspath(PRODUCTION_CLASSES).
                printOnFailure(errorFilename).
                including(SZE_BASE_PACKAGE + "**").
                excluding(SZE_BASE_PACKAGE + CONFIG_PACKAGE + ".**").
                excluding(SZE_BASE_PACKAGE + JOBS_PACKAGE + ".**").
                excluding(SZE_BASE_PACKAGE + CONSTRAINTS_LAYER + ".**").
                excluding(SZE_BASE_PACKAGE + SECURITY_PACKAGE + ".**").
                excluding(SZE_BASE_PACKAGE + UTIL_PACKAGE + ".**").
                withSlicing("dao", SZE_BASE_PACKAGE + "dao.api.(*).**").
                withSlicing("frontend", SZE_BASE_PACKAGE + "frontend.(*).**").
                withSlicing("model", SZE_BASE_PACKAGE + "model.(*).**").
                withSlicing("service", SZE_BASE_PACKAGE + "service.*.(*).**").
                allow("admin", "zeugnis", "zeugnisconfig", "stammdaten", "common");
        assertThat(testObject, is(violationFree()));
    }
}
