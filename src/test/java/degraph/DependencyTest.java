//DependencyTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package degraph;

import static de.schauderhaft.degraph.check.Check.classpath;
import static de.schauderhaft.degraph.check.JCheck.customClasspath;
import static de.schauderhaft.degraph.check.JCheck.violationFree;
import static de.schauderhaft.degraph.check.JLayer.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.schauderhaft.degraph.check.ConstraintBuilder;
import de.schauderhaft.degraph.check.JLayer;

/**
 * Testet verschiedene Abhängigkeiten innerhalb der Anwendung.
 *
 */
public class DependencyTest {

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

    /**
     * Stellt sicher, dass es keine Package-Zyklen gibt. Braucht leider eine
     * Minute :-(
     */
    @Test
    public void cycleFree() {
        assertThat(customClasspath("./target/classes").
                including("net.sf.sze.**"), is(violationFree()));
    }

    /**
     * Prüft, die Zugriffe zwischen den Schichten.
     */
    @Test
    public void layer() {
        ConstraintBuilder testObject =
                // Ich nehme mal nicht den gesamten Pfad. classpath()
                customClasspath("./target/classes")
                .printOnFailure("degraphError.graphml")
                .including("net.sf.sze.**")
                // Util darf von allen Referenziert werden.
                .excluding("net.sf.sze.util.**")
                .withSlicing("sze", "net.sf.sze.(*).**")
                .allowDirect(JLayer.oneOf(FRONTEND_LAYER, JOBS_PACKAGE),
                        anyOf(SERVICE_LAYER, DAO_LAYER, MODEL_PACKAGE),
                        anyOf(CONSTRAINTS_LAYER, UTIL_PACKAGE));
        assertThat(testObject, is(violationFree()));
    }
}
