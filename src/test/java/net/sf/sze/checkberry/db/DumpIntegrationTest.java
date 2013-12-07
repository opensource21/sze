//DumpTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.checkberry.db;




/**
 * Testklasse zum Dumpen von Daten.
 *
 */
public class DumpIntegrationTest extends AbstractSzeDbTestCase {

    /**
     * Erzeugt die DTD.
     * @throws Exception bei Fehlern
     */
    //@Test
    public void createDTD() throws Exception  {
        getTestHandler().createCleanDtd("./src/test/resources/net/sf/sze/checkberry/db/sze-db.dtd");
    }

    /**
     * Exportierte die Stammdaten.
     * @throws Exception bei Fehlern.
     */
    //@Test
    public void dumpStammdaten() throws Exception  {
        getTestHandler().dumpTables("./src/test/resources/net/sf/sze/stammdaten.xml",
                "ARBEITSGRUPPE", "ARBEITS_UND_SOZIAL_VERHALTEN",
                "BEMERKUNGS_BAUSTEIN", "SCHULAMT",
                "SCHULAMTS_BEMERKUNGS_BAUSTEIN", "SCHULFACH", "ZEUGNIS_ART");
    }

    /**
     * Methode um einmal tetsweise was zu dumpen.
     */
    //@Test
    public void dumpTest() {
        getTestHandler().dumpTables("./src/test/resources/net/sf/sze/test.xml",
                "SCHULHALBJAHR");
    }

}
