//DumpTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.checkberry.db;

import org.junit.Test;


/**
 * Testklasse zum Dumpen von Daten.
 *
 */
public class DumpTest extends AbstractSzeDbTestCase {

    /**
     * Erzeugt die DTD.
     * @throws Exception bei Fehlern
     */
    @Test
    public void createDTD() throws Exception  {
        getTestHandler().createCleanDtd("/src/test/resource/net/sf/sze/checkberry/db/sze-db.dtd");
    }

}
