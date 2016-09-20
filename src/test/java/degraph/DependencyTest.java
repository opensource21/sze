//DependencyTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package degraph;

import static org.junit.Assert.*;
import de.schauderhaft.degraph.configuration.NamedPattern;
import static de.schauderhaft.degraph.check.JCheck.*;
import static de.schauderhaft.degraph.check.Check.classpath;
import static de.schauderhaft.degraph.check.JLayer.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * Stellt sicher, dass es keine Package-Zyklen gibt. Braucht leider eine
     * Minute :-(
     */
    @Test
    public void cycleFree() {
        assertThat(classpath().including("net.sf.sze.**"), is(violationFree()));
    }

    @Test
    public void layer() {
        assertThat(
                classpath().including("net.sf.sze.**")
                        .withSlicing(DAO_LAYER, "net.sf.sze.(*).**")
                        .allowDirect(anyOf(FRONTEND_LAYER, JOBS_PACKAGE), SERVICE_LAYER, DAO_LAYER)
                        .allowDirect(anyOf(FRONTEND_LAYER, SERVICE_LAYER, DAO_LAYER), anyOf(MODEL_PACKAGE, UTIL_PACKAGE))
                        // Das VariableUtility sollte nicht von Model benutzt werden.
                        .allowDirect(MODEL_PACKAGE, UTIL_PACKAGE)
                        .allowDirect(UTIL_PACKAGE, MODEL_PACKAGE),
                is(violationFree()));
    }

}
