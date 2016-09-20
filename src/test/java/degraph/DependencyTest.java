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
     * Stellt sicher, dass es keine Package-Zyklen gibt. Braucht leider eine Minute :-(
     */
    @Test
    public void cycleFree() {
        assertThat(classpath().including("net.sf.sze.**"), is(violationFree()));
    }

}
