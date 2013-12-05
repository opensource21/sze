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
     */
    @Test
    public void testInitZeugnis() {
        zeugnisInitialierungsService.initZeugnis(zeugnisFormularDao.findOne(Long.valueOf(1)));
        getTestHandler().dumpTables(
                "./src/test/resources/net/sf/sze/service/impl/"
                + "ZeugnisInitialisierungServiceImplIntegrationTest_testInitZeugnis_result.xml",
                "ZEUGNIS", "AG_BEWERTUNG", "AV_SV_BEWERTUNG", "BEWERTUNG");
    }

}
