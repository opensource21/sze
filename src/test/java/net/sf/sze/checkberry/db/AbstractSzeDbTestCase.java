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
     */
    @Before
    public void setUp() {
      environment =  SpringCheckerberryDbEnvironmentCreator.createEnvironment(
              new SzeDatabaseDescriptionCallback(), new SzeDbConfigurationCallback());
      environment.setUp(this);
    }

    /**
     * Fährt die {@link CheckerberryDbEnvironment} runter.
     */
    @After
    public void tearDown() {
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
