//ZeugnisInitialisierungServiceImplTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


/**
 * Test der die Initialisierungszeit für den App-Kontext übernimmt.
 *
 */
@ContextConfiguration(locations = { "/test-config.xml"})
public class AInitTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private DataSource dataSource;

    /**
     * Tut nix, aber hinterher ist der App-Context initialisiert.
     */
    @Test
    public void init() {
        //fail("TEST");
    }
}
