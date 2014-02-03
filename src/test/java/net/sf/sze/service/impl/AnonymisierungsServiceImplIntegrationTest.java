//AnonymisierungsServiceImplTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl;

import static net.sf.sze.dbunit.rowbuilder.BEMERKUNGRowBuilder.newBEMERKUNG;
import static net.sf.sze.dbunit.rowbuilder.SCHULAMTS_BEMERKUNGRowBuilder.newSCHULAMTS_BEMERKUNG;
import static net.sf.sze.dbunit.rowbuilder.ZEUGNISRowBuilder.newZEUGNIS;
import static org.junit.Assert.*;
import static org.dbunit.validator.Validators.*;

import javax.annotation.Resource;

import net.sf.sze.dbunit.AbstractSzeDbUnitTest;
import net.sf.sze.dbunit.dataset.Anonymisierung;
import net.sf.sze.service.api.AnonymisierungsService;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetRowChanger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Testet die Klasse {@link AnonymisierungsServiceImpl}.
 *
 */
public class AnonymisierungsServiceImplIntegrationTest extends AbstractSzeDbUnitTest {


    @Resource
    private AnonymisierungsService anonymisierungsService;


    /**
     * Test method for
     * {@link net.sf.sze.service.impl.AnonymisierungsServiceImpl#replaceAllNamesWithVariables()}.
     * @throws Exception wenn was schief geht.
     */
    @Test
    public void testReplaceAllNamesWithVariables() throws Exception {
        final IDataSet startDataSet = Anonymisierung.buildVariableInit();
        cleanlyInsert(startDataSet);

        anonymisierungsService.replaceAllNamesWithVariables();

        checkResult(Anonymisierung.buildVariableresult(startDataSet));

    }

    /**
     * Test method for
     * {@link net.sf.sze.service.impl.AnonymisierungsServiceImpl#anonymisierSchueler()}.
     * @throws Exception wenn was schief geht.
     */
    @Test
    public void testAnonymisierSchueler() throws Exception {
        fail();
    }

}
