//AbstractSzeTestCase.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.checkberry.db;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import de.conceptpeople.checkerberry.db.core.CheckerberryDbEnvironment;
import de.conceptpeople.checkerberry.db.core.test.DbTestHandler;
import de.conceptpeople.checkerberry.db.spring.bridge.test.SpringCheckerberryDbEnvironmentCreator;
import de.conceptpeople.checkerberry.db.spring.integration.ApplicationContextResolvingExecutionListener;
import de.conceptpeople.checkerberry.db.spring.integration.TestMethodNameResolvingExecutionListener;


/**
 * Abstrakte Basisklasse für Datenbankabhängige Tests.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml"})
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    TestMethodNameResolvingExecutionListener.class,
    ApplicationContextResolvingExecutionListener.class })
public class AbstractSzeDbTestCase {

    private CheckerberryDbEnvironment environment;

    /**
     * Initialisiert die {@link CheckerberryDbEnvironment}.
     * @throws Exception Subklassen sollen die Möglichkeit haben Fehler zu werfen.
     */
    @Before
    public void setUp() throws Exception {
        environment = SpringCheckerberryDbEnvironmentCreator.createEnvironment();
        environment.setUp(this);
    }

    /**
     * Fährt die {@link CheckerberryDbEnvironment} runter.
     * @throws Exception Subklassen sollen die Möglichkeit haben Fehler zu werfen.
     */
    @After
    public void tearDown() throws Exception  {
        environment.tearDown();
        environment = null;
    }

    /**
     * Liefter die {@link CheckerberryDbEnvironment}.
     * @return die {@link CheckerberryDbEnvironment}.
     */
    protected CheckerberryDbEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Liefter den {@link DbTestHandler}.
     * @return die {@link DbTestHandler}.
     */
    protected DbTestHandler getTestHandler() {
        return getEnvironment().getTestHandler();
    }


}
