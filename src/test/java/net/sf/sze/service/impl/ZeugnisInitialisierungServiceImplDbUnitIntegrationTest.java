//ZeugnisInitialisierungServiceImplTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileOutputStream;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import net.sf.sze.dao.api.zeugnis.ZeugnisDao;
import net.sf.sze.dao.api.zeugnis.ZeugnisFormularDao;
import net.sf.sze.dbunit.SzeBuilderDataSetWriter;
import net.sf.sze.dbunit.SzeDataSet;
import net.sf.sze.dbunit.SzeRowBuilderGenerator;
import net.sf.sze.dbunit.dataset.InitZeugnis;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.service.api.ZeugnisInitialierungsService;
import net.sf.sze.util.ResultContainer;

import org.dbunit.AbstractDatabaseTester;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlWriter;
import org.dbunit.ext.h2.H2Connection;
import org.dbunit.ext.mssql.InsertIdentityOperation;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.validator.ValidatorFailureHandler;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


/**
 * Testet den {@link ZeugnisInitialisierungServiceImpl}.
 *
 */
@ContextConfiguration(locations = { "/test-config.xml"})
public class ZeugnisInitialisierungServiceImplDbUnitIntegrationTest
        extends AbstractJUnit4SpringContextTests {

    @Resource
    private DataSource dataSource;

    private static IDatabaseTester databaseTester = null;

    private static IDataSet deleteDataSet = null;

    @Before
    public void initDatabase() throws Exception {
        if (databaseTester == null) {
            databaseTester = new H2DatabaseTester(dataSource);
        }
        if (deleteDataSet == null) {
            deleteDataSet = new SzeDataSet(databaseTester.getConnection().createDataSet());
        }
    }

    @After
    public void tearDownDb() throws Exception {
        databaseTester.onTearDown();
    }


    @AfterClass
    public static void destroyDatabase() throws Exception {
        deleteDataSet = null;
        databaseTester = null;

    }



    private void cleanlyInsert(IDataSet dataSet) throws Exception {
        InsertIdentityOperation.DELETE.execute(databaseTester.getConnection(),
                deleteDataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
        databaseTester.setDataSet(new SzeDataSet(dataSet));
        databaseTester.onSetup();
    }

    private class H2DatabaseTester extends AbstractDatabaseTester {

        private final DataSource dataSource;

        public H2DatabaseTester(DataSource dataSource) {
            super();
            this.dataSource = dataSource;
        }

        @Override
        public IDatabaseConnection getConnection() throws Exception {
            return new H2Connection(dataSource.getConnection(), getSchema());
        }
    }


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
     * {@link net.sf.sze.service.impl.ZeugnisInitialisierungServiceImpl#initZeugnis(net.sf.sze.model.zeugnis.ZeugnisFormular)}.
     * @throws Exception Fehler
     */
    @Test
    public void testInitZeugnisErstesHalbjahr() throws Exception {
//        SzeRowBuilderGenerator rowBuilder = new SzeRowBuilderGenerator();
//        rowBuilder.generate(databaseTester.getConnection().createDataSet());

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
    private void testInitZeugnis(long formularId) throws Exception {
        //Arrange
        cleanlyInsert(InitZeugnis.buildInitZeugnis());
        final ZeugnisFormular zeugnisFormular = zeugnisFormularDao.findOne(
                Long.valueOf(formularId));
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
        final Long klassenId = zeugnisFormular.getKlasse().getId();
        final Long zeugnisId1 = zeugnisDao.findBySchulhalbjahrIdAndKlasseIdAndSchuelerId(
                schulhalbjahrId, klassenId, Long.valueOf(1)).getId();
        final Long zeugnisId2 = zeugnisDao.findBySchulhalbjahrIdAndKlasseIdAndSchuelerId(
                schulhalbjahrId, klassenId, Long.valueOf(2)).getId();
        final IDataSet expected = InitZeugnis.buildInitResult(zeugnisId1, zeugnisId2,
                zeugnisFormular.getId(), schulhalbjahrId);
        final IDataSet actual = new FilteredDataSet(expected.getTableNames(),
                databaseTester.getConnection().createDataSet());
//        new FlatXmlWriter(new FileOutputStream("result.xml")).write(expected);
        Assertion.assertEquals(expected, new FilteredDataSet(expected.getTableNames(), actual),
                new ValidatorFailureHandler());
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
              + " <li>Bewertung für Textiles Werken wurde ergänzt.</li>\n\t"
              + " <li>Arbeitsgruppe AG Ballspiele wurde hinzugefügt.</li>\n\t"
              + " <li>Arbeitsgruppe AG Theater wurde gelöscht.</li>\n\t"
              + " <li>Arbeits- und Sozialverhalten Lern-/Leistungsbereitschaft / Mitarbeit wurde gelöscht.</li>\n\t"
              + " <li>Arbeits- und Sozialverhalten Ziel- und Ergebnisorientierung wurde hinzugefügt.</li></ul>",
              "Für den Schüler MUSTERFRAU, ERNA und das Halbjahr 2012/13 1. Hj. existiert schon ein Zeugnis.<ul>\n\t"
              + " <li>Bewertung für Deutsch wurde konvertiert von AussenDifferenzierteBewertung nach BinnenDifferenzierteBewertung</li>\n\t"
              + " <li>Bewertung für Englisch wurde konvertiert von AussenDifferenzierteBewertung nach StandardBewertung</li>\n\t"
              + " <li>Bewertung für Naturwissenschaften wurde konvertiert von BinnenDifferenzierteBewertung nach StandardBewertung</li>\n\t"
              + " <li>Bewertung für Gesellschaftslehre wurde konvertiert von BinnenDifferenzierteBewertung nach AussenDifferenzierteBewertung</li>\n\t"
              + " <li>Bewertung für Musik wurde gelöscht.</li>\n\t"
              + " <li>Bewertung für Kunst wurde konvertiert von StandardBewertung nach BinnenDifferenzierteBewertung</li>\n\t"
              + " <li>Bewertung für EDV wurde konvertiert von StandardBewertung nach AussenDifferenzierteBewertung</li>\n\t"
              + " <li>Bewertung für Textiles Werken wurde ergänzt.</li>\n\t"
              + " <li>Arbeitsgruppe AG Ballspiele wurde hinzugefügt.</li>\n\t"
              + " <li>Arbeitsgruppe AG Theater wurde gelöscht.</li>\n\t"
              + " <li>Arbeits- und Sozialverhalten Lern-/Leistungsbereitschaft / Mitarbeit wurde gelöscht.</li>\n\t"
              + " <li>Arbeits- und Sozialverhalten Ziel- und Ergebnisorientierung wurde hinzugefügt.</li></ul>");
         //J+
//        dumpResult();
        //getTestHandler().assertEqualsExpected();

    }
    //CSON: LineLength


    /**
     * Methode zum Dumpen des erwarteten Ergebnis.
     */
    @SuppressWarnings("unused")
    private void dumpResult() throws Exception {

        SzeBuilderDataSetWriter writer = new SzeBuilderDataSetWriter("net.sf.sze.dbunit.dataset",
                "ResultDS");
        writer.write(new FilteredDataSet(new String[]
                {"ZEUGNIS", "AG_BEWERTUNG", "AV_SV_BEWERTUNG", "BEWERTUNG"},
                databaseTester.getConnection().createDataSet()));
    }

}
