//ZeugnisInitialisierungServiceImplTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl.zeugnisconfig;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.ZeugnisDao;
import net.sf.sze.dao.api.zeugnis.ZeugnisFormularDao;
import net.sf.sze.dbunit.AbstractSzeDbUnitTest;
import net.sf.sze.dbunit.dataset.InitZeugnis;
import net.sf.sze.dbunit.dataset.UpdateZeugnis;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.service.api.zeugnis.ZeugnisInitialierungsService;
import net.sf.sze.service.impl.zeugnis.ZeugnisInitialisierungServiceImpl;
import net.sf.sze.util.ResultContainer;

import org.dbunit.dataset.IDataSet;
import org.junit.Test;


/**
 * Testet den {@link ZeugnisInitialisierungServiceImpl}.
 *
 */
public class ZeugnisInitialisierungServiceImplDbUnitIntegrationTest
        extends AbstractSzeDbUnitTest {


    @Resource
    private ZeugnisInitialierungsService zeugnisInitialierungsService;

    /**
     * Dao für die {@link ZeugnisFormular}.
     */
    @Resource
    private ZeugnisFormularDao zeugnisFormularDao;

    /**
     * Dao für die {@link Zeugnis}.
     */
    @Resource
    private ZeugnisDao zeugnisDao;

    /**
     * Test method for
     * {@link net.sf.sze.service.impl.zeugnis.ZeugnisInitialisierungServiceImpl#initZeugnis(net.sf.sze.model.zeugnis.ZeugnisFormular)}.
     * @throws Exception Fehler
     */
    @Test
    public void testInitZeugnisErstesHalbjahr() throws Exception {
        testInitZeugnis(2);
    }

    /**
     * Test method for
     * {@link net.sf.sze.service.impl.zeugnis.ZeugnisInitialisierungServiceImpl#initZeugnis(net.sf.sze.model.zeugnis.ZeugnisFormular)}.
     * @throws Exception Fehler
     */
    @Test
    public void testInitZeugnisZweitesHalbjahr() throws Exception {
        testInitZeugnis(3);
    }

    /**
     * @param formularId
     * @throws SQLException
     */
    //CSOFF: LineLength Ist so übersichtlicher
    private void testInitZeugnis(long formularId) throws Exception {
        //Arrange
        cleanlyInsert(InitZeugnis.buildInitZeugnis(formularId == 3));
        final ZeugnisFormular zeugnisFormular = zeugnisFormularDao.findOne(
                Long.valueOf(formularId));
        assertThat(zeugnisFormular).as("Zeugnisfomrular mit der Id " + formularId
                + " kann nicht gefunden werden.").isNotNull();
        //Act
        ResultContainer result = zeugnisInitialierungsService.initZeugnis(zeugnisFormular);
        //Assert
        assertThat(result.getErrors()).isEmpty();
        assertThat(result.getMessages()).hasSize(2).contains(
                "Für den Schüler MUSTERMANN, ERWIN und das Halbjahr "
                 + zeugnisFormular.getSchulhalbjahr() + " wurde ein Zeugnis anlegt.",
                "Für den Schüler MUSTERFRAU, ERNA und das Halbjahr "
                + zeugnisFormular.getSchulhalbjahr() + " wurde ein Zeugnis anlegt.");

        final Long schulhalbjahrId = zeugnisFormular.getSchulhalbjahr().getId();
        final Long zeugnisId1 = zeugnisDao.findByFormularSchulhalbjahrIdAndSchuelerId(
                schulhalbjahrId, Long.valueOf(1)).getId();
        final Long zeugnisId2 = zeugnisDao.findByFormularSchulhalbjahrIdAndSchuelerId(
                schulhalbjahrId, Long.valueOf(2)).getId();
        final IDataSet expected = InitZeugnis.buildInitResult(zeugnisId1, zeugnisId2,
                zeugnisFormular.getId(), formularId == 3);
        checkResult(expected);
    }



    /**
     * Test method for
     * {@link net.sf.sze.service.impl.zeugnis.ZeugnisInitialisierungServiceImpl#initZeugnis(net.sf.sze.model.zeugnis.ZeugnisFormular)}.
     * @throws Exception Fehler
     */
    @Test
    //CSOFF: LineLength Ist so übersichtlicher
    public void testInitZeugnisUpdate() throws Exception {
        cleanlyInsert(UpdateZeugnis.buildUpdateInit());
        final ZeugnisFormular zeugnisFormular = zeugnisFormularDao.findOne(
                Long.valueOf(2));
        ResultContainer result = zeugnisInitialierungsService.initZeugnis(zeugnisFormular);
        assertThat(result.getErrors()).isEmpty();
        //J-
        assertThat(result.getMessages()).hasSize(2).contains(
              "Für den Schüler MUSTERMANN, ERWIN und das Halbjahr 2012/13 1. Hj. existiert schon ein Zeugnis.<ul>\n\t"
              + " <li>Bewertung für Deutsch wurde konvertiert von ZweiNiveauBewertung nach DreiNiveauBewertung</li>\n\t"
              + " <li>Bewertung für Englisch wurde konvertiert von ZweiNiveauBewertung nach StandardBewertung</li>\n\t"
              + " <li>Bewertung für Naturwissenschaften wurde konvertiert von DreiNiveauBewertung nach StandardBewertung</li>\n\t"
              + " <li>Bewertung für Gesellschaftslehre wurde konvertiert von DreiNiveauBewertung nach ZweiNiveauBewertung</li>\n\t"
              + " <li>Bewertung für Musik wurde gelöscht.</li>\n\t"
              + " <li>Bewertung für Kunst wurde konvertiert von StandardBewertung nach DreiNiveauBewertung</li>\n\t"
              + " <li>Bewertung für EDV wurde konvertiert von StandardBewertung nach ZweiNiveauBewertung</li>\n\t"
              + " <li>Bewertung für Textiles Werken wurde ergänzt.</li>\n\t"
              + " <li>Arbeitsgruppe AG Ballspiele wurde hinzugefügt.</li>\n\t"
              + " <li>Arbeitsgruppe AG Theater wurde gelöscht.</li>\n\t"
              + " <li>Arbeits- und Sozialverhalten Lern-/Leistungsbereitschaft / Mitarbeit wurde gelöscht.</li>\n\t"
              + " <li>Arbeits- und Sozialverhalten Ziel- und Ergebnisorientierung wurde hinzugefügt.</li></ul>",
              "Für den Schüler MUSTERFRAU, ERNA und das Halbjahr 2012/13 1. Hj. existiert schon ein Zeugnis.<ul>\n\t"
              + " <li>Bewertung für Deutsch wurde konvertiert von ZweiNiveauBewertung nach DreiNiveauBewertung</li>\n\t"
              + " <li>Bewertung für Englisch wurde konvertiert von ZweiNiveauBewertung nach StandardBewertung</li>\n\t"
              + " <li>Bewertung für Naturwissenschaften wurde konvertiert von DreiNiveauBewertung nach StandardBewertung</li>\n\t"
              + " <li>Bewertung für Gesellschaftslehre wurde konvertiert von DreiNiveauBewertung nach ZweiNiveauBewertung</li>\n\t"
              + " <li>Bewertung für Musik wurde gelöscht.</li>\n\t"
              + " <li>Bewertung für Kunst wurde konvertiert von StandardBewertung nach DreiNiveauBewertung</li>\n\t"
              + " <li>Bewertung für EDV wurde konvertiert von StandardBewertung nach ZweiNiveauBewertung</li>\n\t"
              + " <li>Bewertung für Textiles Werken wurde ergänzt.</li>\n\t"
              + " <li>Arbeitsgruppe AG Ballspiele wurde hinzugefügt.</li>\n\t"
              + " <li>Arbeitsgruppe AG Theater wurde gelöscht.</li>\n\t"
              + " <li>Arbeits- und Sozialverhalten Lern-/Leistungsbereitschaft / Mitarbeit wurde gelöscht.</li>\n\t"
              + " <li>Arbeits- und Sozialverhalten Ziel- und Ergebnisorientierung wurde hinzugefügt.</li></ul>");
         //J+
        final Long schulhalbjahrId = zeugnisFormular.getSchulhalbjahr().getId();
        final Long zeugnisId1 = zeugnisDao.findByFormularSchulhalbjahrIdAndSchuelerId(
                schulhalbjahrId, Long.valueOf(1)).getId();
        final Long zeugnisId2 = zeugnisDao.findByFormularSchulhalbjahrIdAndSchuelerId(
                schulhalbjahrId, Long.valueOf(2)).getId();
        final IDataSet expected = UpdateZeugnis.buildUpdateResult(zeugnisId1, zeugnisId2);
        checkResult(expected);
    }
    //CSON: LineLength

}
