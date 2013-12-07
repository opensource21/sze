//ZeugnisInitialisierungServiceImplTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import javax.annotation.Resource;

import net.sf.sze.checkberry.db.AbstractSzeDbTestCase;
import net.sf.sze.dao.api.zeugnis.ZeugnisFormularDao;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.service.api.ZeugnisInitialierungsService;
import net.sf.sze.util.ResultContainer;

import org.junit.Test;

import de.conceptpeople.checkerberry.db.bridge.context.ParameterContext;
import de.conceptpeople.checkerberry.db.core.test.NoCache;


/**
 * Testet den {@link ZeugnisInitialisierungServiceImpl}.
 *
 */
@NoCache
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
        testInitZeugnis(1);
    }

    /**
     * Test method for
     * {@link net.sf.sze.service.impl.ZeugnisInitialisierungServiceImpl#initZeugnis(net.sf.sze.model.zeugnis.ZeugnisFormular)}.
     * @throws Exception Fehler
     */
    @Test
    public void testInitZeugnisZweitesHalbjahr() throws Exception {
        testInitZeugnis(2);
    }

    /**
     * @param formularId
     * @throws SQLException
     */
    private void testInitZeugnis(long formularId) throws SQLException {
        final ZeugnisFormular zeugnisFormular = zeugnisFormularDao.findOne(
                Long.valueOf(formularId));
        ResultContainer result = zeugnisInitialierungsService.initZeugnis(zeugnisFormular);
        assertThat(result.getErrors()).isEmpty();
        assertThat(result.getMessages()).hasSize(2).contains(
                "Für den Schüler MUSTERMANN, ERWIN und das Halbjahr "
                 + zeugnisFormular.getSchulhalbjahr() + " wurde ein Zeugnis anlegt.",
                "Für den Schüler MUSTERFRAU, ERNA und das Halbjahr "
                + zeugnisFormular.getSchulhalbjahr() + " wurde ein Zeugnis anlegt.");

        final ParameterContext parameterContext = getTestHandler().getParameterContext();
        parameterContext.addParameter("formularId", zeugnisFormular.getId());
        parameterContext.addParameter("halbjahrId", zeugnisFormular.getSchulhalbjahr().getId());
//        getTestHandler().createDiffReport("initZeugnisAbweichung.html");
        getTestHandler().assertEqualsExpected();
    }

    /**
     * Test method for
     * {@link net.sf.sze.service.impl.ZeugnisInitialisierungServiceImpl#initZeugnis(net.sf.sze.model.zeugnis.ZeugnisFormular)}.
     * @throws Exception Fehler
     */
    @Test
    //CSOFF: LineLength Ist so übersichtlicher
    public void testInitZeugnisUpdate() throws Exception {
        final ZeugnisFormular zeugnisFormular = zeugnisFormularDao.findOne(
                Long.valueOf(1));
        ResultContainer result = zeugnisInitialierungsService.initZeugnis(zeugnisFormular);
        assertThat(result.getErrors()).isEmpty();
        //J-
        assertThat(result.getMessages()).hasSize(2).contains(
              "Für den Schüler MUSTERMANN, ERWIN und das Halbjahr 2012/13 1. Hj. existiert schon ein Zeugnis.<ul>\n\t"
              + " <li>Bewertung für Deutsch wurde konvertiert von AussenDifferenzierteBewertung nach BinnenDifferenzierteBewertung</li>\n\t"
              + " <li>Bewertung für Englisch wurde konvertiert von AussenDifferenzierteBewertung nach StandardBewertung</li>\n\t"
              + " <li>Bewertung für Naturwissenschaften wurde konvertiert von BinnenDifferenzierteBewertung nach StandardBewertung</li>\n\t"
              + " <li>Bewertung für Gesellschaftslehre wurde konvertiert von BinnenDifferenzierteBewertung nach AussenDifferenzierteBewertung</li>\n\t"
              + " <li>Bewertung für Musik wurde gelöscht.</li>\n\t"
              + " <li>Bewertung für Kunst wurde konvertiert von StandardBewertung nach BinnenDifferenzierteBewertung</li>\n\t"
              + " <li>Bewertung für EDV wurde konvertiert von StandardBewertung nach AussenDifferenzierteBewertung</li>\n\t"
              + " <li>Bewertung für Textiles Werken wurde ergänzt.</li></ul>",
              "Für den Schüler MUSTERFRAU, ERNA und das Halbjahr 2012/13 1. Hj. existiert schon ein Zeugnis.<ul>\n\t"
              + " <li>Bewertung für Deutsch wurde konvertiert von AussenDifferenzierteBewertung nach BinnenDifferenzierteBewertung</li>\n\t"
              + " <li>Bewertung für Englisch wurde konvertiert von AussenDifferenzierteBewertung nach StandardBewertung</li>\n\t"
              + " <li>Bewertung für Naturwissenschaften wurde konvertiert von BinnenDifferenzierteBewertung nach StandardBewertung</li>\n\t"
              + " <li>Bewertung für Gesellschaftslehre wurde konvertiert von BinnenDifferenzierteBewertung nach AussenDifferenzierteBewertung</li>\n\t"
              + " <li>Bewertung für Musik wurde gelöscht.</li>\n\t"
              + " <li>Bewertung für Kunst wurde konvertiert von StandardBewertung nach BinnenDifferenzierteBewertung</li>\n\t"
              + " <li>Bewertung für EDV wurde konvertiert von StandardBewertung nach AussenDifferenzierteBewertung</li>\n\t"
              + " <li>Bewertung für Textiles Werken wurde ergänzt.</li></ul>");
         //J+
//        dumpResult();
        getTestHandler().assertEqualsExpected();

    }
    //CSON: LineLength


    /**
     * Methode zum Dumpen des erwarteten Ergebnis.
     */
    @SuppressWarnings("unused")
    private void dumpResult() {
        getTestHandler().dumpTables(
                "./src/test/resources/net/sf/sze/service/impl/"
                + "ZeugnisInitialisierungServiceImplIntegrationTest_"
                + "testInitZeugnisUpdate_result.xml",
                "ZEUGNIS", "AG_BEWERTUNG", "AV_SV_BEWERTUNG", "BEWERTUNG");
    }

}
