//ZeugnisInitialisierungServiceImplTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl;

import javax.annotation.Resource;

import net.sf.sze.checkberry.db.AbstractSzeDbTestCase;
import net.sf.sze.dao.api.zeugnis.ZeugnisFormularDao;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.service.api.ZeugnisInitialierungsService;

import org.junit.Test;


/**
 * Testet den {@link ZeugnisInitialisierungServiceImpl}.
 *
 */
public class ZeugnisInitialisierungServiceImplIntegrationTest extends AbstractSzeDbTestCase {

    @Resource
    private ZeugnisInitialierungsService zeugnisInitialierungsService;

    /**
     * Dao für die {@link ZeugnisFormular}.
     */
    @Resource
    private ZeugnisFormularDao zeugnisFormularDao;


    /**
     * Test method for
     * {@link net.sf.sze.service.impl.ZeugnisInitialisierungServiceImpl#initZeugnis(net.sf.sze.model.zeugnis.ZeugnisFormular)}.
     * @throws Exception Fehler
     */
    @Test
    public void testInitZeugnisErstesHalbjahr() throws Exception {
        zeugnisInitialierungsService.initZeugnis(zeugnisFormularDao.findOne(Long.valueOf(1)));
//        getTestHandler().createDiffReport("initZeugnisAbweichung.html");
        getTestHandler().assertEqualsExpected();

    }

    /**
     * Test method for
     * {@link net.sf.sze.service.impl.ZeugnisInitialisierungServiceImpl#initZeugnis(net.sf.sze.model.zeugnis.ZeugnisFormular)}.
     * @throws Exception Fehler
     */
    @Test
    public void testInitZeugnisZweitesHalbjahr() throws Exception {
        zeugnisInitialierungsService.initZeugnis(zeugnisFormularDao.findOne(Long.valueOf(2)));
//        getTestHandler().createDiffReport("initZeugnisAbweichung.html");
        //dumpResult();
        getTestHandler().assertEqualsExpected();

    }

    /**
     * Methode zum Dumpen des erwarteten Ergebnis.
     */
    @SuppressWarnings("unused")
    private void dumpResult() {
        getTestHandler().dumpTables(
                "./src/test/resources/net/sf/sze/service/impl/"
                + "ZeugnisInitialisierungServiceImplIntegrationTest_"
                + "testInitZeugnisZweitesHalbjahr_result.xml",
                "ZEUGNIS", "AG_BEWERTUNG", "AV_SV_BEWERTUNG", "BEWERTUNG");
    }

}
