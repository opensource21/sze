//SchulhalbjahrServiceImplTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl.zeugnisconfig;

import java.util.Calendar;

import javax.annotation.Resource;

import net.sf.sze.dbunit.AbstractSzeDbUnitTest;
import net.sf.sze.dbunit.dataset.SchulhalbjahrInit;
import net.sf.sze.service.api.zeugnisconfig.SchulhalbjahrService;

import org.junit.Test;

/**
 * Test of complex-methods in {@link SchulhalbjahrServiceImpl}.
 *
 */
public class SchulhalbjahrServiceImplDbUnitIntegrationTest extends AbstractSzeDbUnitTest {

    @Resource
    private SchulhalbjahrService schulhalbjahrService;



    /**
     * Test method for
     * {@link net.sf.sze.service.impl.zeugnisconfig.SchulhalbjahrServiceImpl#init(java.util.Calendar)}.
     * @throws Exception wenn was schief geht.
     */
    @Test
    public void testInitErstesHalbjahr() throws Exception {
        super.cleanlyInsert(SchulhalbjahrInit.buildInitErstesHalbjahr());
        schulhalbjahrService.init(createDate(2013, 8, 1));
        super.checkResult(SchulhalbjahrInit.buildResultErstesHalbjahr());
    }

    /**
     * Test method for
     * {@link net.sf.sze.service.impl.zeugnisconfig.SchulhalbjahrServiceImpl#init(java.util.Calendar)}.
     * @throws Exception wenn was schief geht.
     */
    @Test
    public void testInitZweitesHalbjahr() throws Exception {
        super.cleanlyInsert(SchulhalbjahrInit.buildInitZweitesHalbjahr());
        schulhalbjahrService.init(createDate(2014, 3, 1));
        super.getSzeDatebase().dumpResult("Result", "schulhalbjahr");
        super.checkResult(SchulhalbjahrInit.buildResultZweitesHalbjahr());
    }


    private static Calendar createDate(int year, int month, int day) {
        final Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return cal;
    }

}
