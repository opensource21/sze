//ZeugnisFormularServiceImplTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl.zeugnis;

import java.util.Calendar;

import javax.annotation.Resource;

import net.sf.sze.dbunit.AbstractSzeDbUnitTest;
import net.sf.sze.dbunit.dataset.ZeugnisFormularInit;
import net.sf.sze.service.api.zeugnis.ZeugnisFormularService;

import org.junit.FixMethodOrder;
import org.junit.Test;


/**
 * Testet die Initialisierung von ZeugnisFormularen.
 *
 */
@FixMethodOrder
public class ZeugnisFormularServiceImplDbUnitIntegrationTest extends AbstractSzeDbUnitTest {

    @Resource
    private ZeugnisFormularService zeugnisFormularService;

    /**
     * Test method for
     * {@link net.sf.sze.service.impl.zeugnis.ZeugnisFormularServiceImpl#init(java.util.Calendar)}.
     * @throws Exception wenn was schief geht.
     */
    @Test
    public void testInit1Hj() throws Exception {
        super.cleanlyInsert(ZeugnisFormularInit.buildInitZeugnisFormular1Hj());
        zeugnisFormularService.init(createDate(2013, 10, 1));
        super.checkResult(ZeugnisFormularInit.buildErgebnisInit1HjDataSet());
        zeugnisFormularService.init(createDate(2013, 10, 1));
        super.checkResult(ZeugnisFormularInit.buildErgebnisInit1HjDataSet());
    }

    /**
     * Test method for
     * {@link net.sf.sze.service.impl.zeugnis.ZeugnisFormularServiceImpl#init(java.util.Calendar)}.
     * @throws Exception wenn was schief geht.
     */
    @Test
    public void testInit2Hj() throws Exception {
        super.cleanlyInsert(ZeugnisFormularInit.buildInitZeugnisFormular2Hj());
        zeugnisFormularService.init(createDate(2013, 3, 1));
        super.checkResult(ZeugnisFormularInit.buildErgebnisInit2HjDataSet());
        zeugnisFormularService.init(createDate(2013, 4, 1));
        super.checkResult(ZeugnisFormularInit.buildErgebnisInit2HjDataSet());
    }

    private static Calendar createDate(int year, int month, int day) {
        final Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return cal;
    }
}
