//AbstractSzeDbUnitTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.dbunit;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import net.sf.sze.dbunit.rowbuilder.AG_BEWERTUNGRowBuilder;
import net.sf.sze.dbunit.rowbuilder.ARBEITSGRUPPERowBuilder;
import net.sf.sze.dbunit.rowbuilder.ARBEITS_UND_SOZIAL_VERHALTENRowBuilder;
import net.sf.sze.dbunit.rowbuilder.AV_SV_BEWERTUNGRowBuilder;
import net.sf.sze.dbunit.rowbuilder.BEMERKUNGRowBuilder;
import net.sf.sze.dbunit.rowbuilder.BEMERKUNGS_BAUSTEINRowBuilder;
import net.sf.sze.dbunit.rowbuilder.BEWERTUNGRowBuilder;
import net.sf.sze.dbunit.rowbuilder.KLASSERowBuilder;
import net.sf.sze.dbunit.rowbuilder.SCHUELERRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SCHULAMTRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SCHULAMTS_BEMERKUNGRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SCHULFACHRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SCHULFACH_DETAIL_INFORowBuilder;
import net.sf.sze.dbunit.rowbuilder.SCHULHALBJAHRRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SOLBEWERTUNGS_TEXTRowBuilder;
import net.sf.sze.dbunit.rowbuilder.ZEUGNISRowBuilder;
import net.sf.sze.dbunit.rowbuilder.ZEUGNIS_ARTRowBuilder;
import net.sf.sze.dbunit.rowbuilder.ZEUGNIS_FORMULARRowBuilder;

import org.dbunit.AbstractDatabaseTester;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableIterator;
import org.dbunit.dataset.SortedTable;
import org.dbunit.ext.h2.H2Connection;
import org.dbunit.ext.mssql.InsertIdentityOperation;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.validator.ValidatorFailureHandler;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


/**
 * Class AbstractSzeDbUnitTest
 *
 */
@ContextConfiguration(locations = { "/test-config.xml"})
public abstract class AbstractSzeDbUnitTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private DataSource dataSource;

    private static IDatabaseTester databaseTester = null;

    private static IDataSet deleteDataSet = null;

    private static Map<String, String[]> tableToUniqueKey = new HashMap<>();

    static {
        tableToUniqueKey.put(ARBEITSGRUPPERowBuilder.TABLE_NAME,
                new String[] { ARBEITSGRUPPERowBuilder.C_NAME });
        tableToUniqueKey.put(ARBEITS_UND_SOZIAL_VERHALTENRowBuilder.TABLE_NAME,
                new String[] { ARBEITS_UND_SOZIAL_VERHALTENRowBuilder.C_TYP,
                ARBEITS_UND_SOZIAL_VERHALTENRowBuilder.C_NAME });
        tableToUniqueKey.put(BEMERKUNGS_BAUSTEINRowBuilder.TABLE_NAME,
                new String[] { BEMERKUNGS_BAUSTEINRowBuilder.C_NAME });
        tableToUniqueKey.put(SCHULAMTRowBuilder.TABLE_NAME, new String[] { SCHULAMTRowBuilder.C_NAME });
        tableToUniqueKey.put(SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder.TABLE_NAME,
                new String[] { SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder.C_NAME });
        tableToUniqueKey.put(SCHULFACHRowBuilder.TABLE_NAME,
                new String[] { SCHULFACHRowBuilder.C_TYP,  SCHULFACHRowBuilder.C_NAME});
        tableToUniqueKey.put(SCHULFACH_DETAIL_INFORowBuilder.TABLE_NAME, new String[] {
                SCHULFACH_DETAIL_INFORowBuilder.C_FORMULAR_ID, SCHULFACH_DETAIL_INFORowBuilder.C_SCHULFACH_ID });
        tableToUniqueKey.put(ZEUGNIS_ARTRowBuilder.TABLE_NAME,
                new String[] { ZEUGNIS_ARTRowBuilder.C_NAME });

        // Stammdaten
        tableToUniqueKey.put(KLASSERowBuilder.TABLE_NAME,
                new String[] { KLASSERowBuilder.C_JAHRGANG, KLASSERowBuilder.C_SUFFIX });

        tableToUniqueKey.put(SCHUELERRowBuilder.TABLE_NAME, new String[] { "ID" });

        // Zeugnisdaten
        tableToUniqueKey.put(SCHULHALBJAHRRowBuilder.TABLE_NAME,
                new String[] { SCHULHALBJAHRRowBuilder.C_JAHR, SCHULHALBJAHRRowBuilder.C_HALBJAHR });
        tableToUniqueKey.put(ZEUGNISRowBuilder.TABLE_NAME,
                new String[] { ZEUGNISRowBuilder.C_FORMULAR_ID,
                ZEUGNISRowBuilder.C_KLASSE_ID, ZEUGNISRowBuilder.C_SCHUELER_ID });
        tableToUniqueKey.put(ZEUGNIS_FORMULARRowBuilder.TABLE_NAME, new String[] { ZEUGNIS_FORMULARRowBuilder.C_SCHULHALBJAHR_ID,
                ZEUGNIS_FORMULARRowBuilder.C_KLASSE_ID, ZEUGNIS_FORMULARRowBuilder.C_BESCHREIBUNG });

        // Bewertungen
        tableToUniqueKey.put(AG_BEWERTUNGRowBuilder.TABLE_NAME,
                new String[] { AG_BEWERTUNGRowBuilder.C_ARBEITSGRUPPE_ID,
                AG_BEWERTUNGRowBuilder.C_ZEUGNIS_ID });
        tableToUniqueKey.put(AV_SV_BEWERTUNGRowBuilder.TABLE_NAME, new String[] {
                AV_SV_BEWERTUNGRowBuilder.C_ARBEITS_UND_SOZIAL_VERHALTEN_ID, AV_SV_BEWERTUNGRowBuilder.C_ZEUGNIS_ID });
        tableToUniqueKey.put(BEWERTUNGRowBuilder.TABLE_NAME, new String[] {
                BEWERTUNGRowBuilder.C_SCHULFACH_ID, BEWERTUNGRowBuilder.C_ZEUGNIS_ID });

        tableToUniqueKey.put(BEMERKUNGRowBuilder.TABLE_NAME, new String[] { "ID" });
        tableToUniqueKey.put(SCHULAMTS_BEMERKUNGRowBuilder.TABLE_NAME, new String[] { "ID" });
        tableToUniqueKey.put(SOLBEWERTUNGS_TEXTRowBuilder.TABLE_NAME, new String[] { SOLBEWERTUNGS_TEXTRowBuilder.C_NAME });
    }

    /**
     * Initialisiert  DBUnit.
     * @throws Exception wenn was schief geht.
     */
    @Before
    public void initDatabase() throws Exception {
        if (databaseTester == null) {
            databaseTester = new H2DatabaseTester(dataSource);
        }
        if (deleteDataSet == null) {
            deleteDataSet = new SzeDataSet(databaseTester.getConnection().createDataSet());
        }
    }

    /**
     * Sagt DbUnit das der Test vorbei ist.
     * @throws Exception wenn was schief geht.
     */
    @After
    public void tearDownDb() throws Exception {
        databaseTester.onTearDown();
    }


    /**
     * Fährt die Datenbank runter.
     * @throws Exception wenn was schief geht.
     */
    @AfterClass
    public static void destroyDatabase() throws Exception {
        deleteDataSet = null;
        databaseTester = null;

    }



    public  void cleanlyInsert(IDataSet dataSet) throws Exception {
        InsertIdentityOperation.DELETE.execute(databaseTester.getConnection(),
                deleteDataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
        databaseTester.setDataSet(new SzeDataSet(dataSet));
        databaseTester.onSetup();
    }


    public void checkResult(final IDataSet expected) throws Exception {
        final IDataSet actual = databaseTester.getConnection().createDataSet();
        final ITableIterator expectdTables = expected.iterator();
        while (expectdTables.next()) {
            final String tableName = expectdTables.getTable().getTableMetaData().getTableName();
            final String[] uk = tableToUniqueKey.get(tableName);
            final ITable expectedTable  = new SortedTable(expected.getTable(tableName), uk);
            final ITable actualTable = new SortedTable(actual.getTable(tableName), uk);
            Assertion.assertEquals(expectedTable, actualTable, new ValidatorFailureHandler());
        }

    }

    /**
     * Methode zum Dumpen des erwarteten Ergebnis.
     */
    public void dumpResult(String fileName, String... tableNames) throws Exception {
        final SzeBuilderDataSetWriter writer = new SzeBuilderDataSetWriter(
                "net.sf.sze.dbunit.dataset", fileName);
        if (tableNames != null && tableNames.length > 0) {
            writer.write(new FilteredDataSet(tableNames,
                    databaseTester.getConnection().createDataSet()));
        } else {
            writer.write(deleteDataSet);
        }
    }

    public void generateRowBuilder() throws Exception {
          SzeRowBuilderGenerator rowBuilder = new SzeRowBuilderGenerator();
          rowBuilder.generate(databaseTester.getConnection().createDataSet());
    }


    /**
     *  Class H2DatabaseTester.
     *
     */
    private class H2DatabaseTester extends AbstractDatabaseTester {

        private final DataSource dataSource;

        public H2DatabaseTester(DataSource dataSource) {
            super();
            this.dataSource = dataSource;
        }

        @Override
        public IDatabaseConnection getConnection() throws Exception {
            final IDatabaseConnection connection =
                    new H2Connection(dataSource.getConnection(), getSchema());
            String id = "http://www.dbunit.org/features/batchedStatements";
            DatabaseConfig config = connection.getConfig();
            config.setProperty(id, Boolean.TRUE);
            return connection;
        }
    }



}
