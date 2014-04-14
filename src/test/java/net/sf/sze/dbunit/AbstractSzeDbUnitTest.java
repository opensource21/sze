//AbstractSzeDbUnitTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.dbunit;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import net.sf.sze.dbunit.rowbuilder.AgBewertungRowBuilder;
import net.sf.sze.dbunit.rowbuilder.ArbeitsUndSozialVerhaltenRowBuilder;
import net.sf.sze.dbunit.rowbuilder.ArbeitsgruppeRowBuilder;
import net.sf.sze.dbunit.rowbuilder.AvSvBewertungRowBuilder;
import net.sf.sze.dbunit.rowbuilder.BemerkungRowBuilder;
import net.sf.sze.dbunit.rowbuilder.BemerkungsBausteinRowBuilder;
import net.sf.sze.dbunit.rowbuilder.BewertungRowBuilder;
import net.sf.sze.dbunit.rowbuilder.KlasseRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SchuelerRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SchulamtRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SchulamtsBemerkungRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SchulamtsBemerkungsBausteinRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SchulfachDetailInfoRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SchulfachRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SchulhalbjahrRowBuilder;
import net.sf.sze.dbunit.rowbuilder.SolbewertungsTextRowBuilder;
import net.sf.sze.dbunit.rowbuilder.ZeugnisArtRowBuilder;
import net.sf.sze.dbunit.rowbuilder.ZeugnisFormularRowBuilder;
import net.sf.sze.dbunit.rowbuilder.ZeugnisRowBuilder;

import org.dbunit.AbstractDatabaseTester;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.DefaultOperationListener;
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
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.validator.ValidatorFailureHandler;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;
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

    /**
     * DB-Schema.
     */
    @Value("${db.schema}")
    private String schema;


    private static IDatabaseTester databaseTester = null;

    private static IDataSet deleteDataSet = null;

    private static Map<String, String[]> tableToUniqueKey = new HashMap<>();

    static {
        tableToUniqueKey.put(ArbeitsgruppeRowBuilder.TABLE_NAME,
                new String[] { ArbeitsgruppeRowBuilder.C_NAME });
        tableToUniqueKey.put(ArbeitsUndSozialVerhaltenRowBuilder.TABLE_NAME,
                new String[] { ArbeitsUndSozialVerhaltenRowBuilder.C_TYP,
                ArbeitsUndSozialVerhaltenRowBuilder.C_NAME });
        tableToUniqueKey.put(BemerkungsBausteinRowBuilder.TABLE_NAME,
                new String[] { BemerkungsBausteinRowBuilder.C_NAME });
        tableToUniqueKey.put(SchulamtRowBuilder.TABLE_NAME, new String[] { SchulamtRowBuilder.C_NAME });
        tableToUniqueKey.put(SchulamtsBemerkungsBausteinRowBuilder.TABLE_NAME,
                new String[] { SchulamtsBemerkungsBausteinRowBuilder.C_NAME });
        tableToUniqueKey.put(SchulfachRowBuilder.TABLE_NAME,
                new String[] { SchulfachRowBuilder.C_TYP,  SchulfachRowBuilder.C_NAME});
        tableToUniqueKey.put(SchulfachDetailInfoRowBuilder.TABLE_NAME, new String[] {
                SchulfachDetailInfoRowBuilder.C_FORMULAR_ID, SchulfachDetailInfoRowBuilder.C_SCHULFACH_ID });
        tableToUniqueKey.put(ZeugnisArtRowBuilder.TABLE_NAME,
                new String[] { ZeugnisArtRowBuilder.C_NAME });

        // Stammdaten
        tableToUniqueKey.put(KlasseRowBuilder.TABLE_NAME,
                new String[] { KlasseRowBuilder.C_JAHRGANG, KlasseRowBuilder.C_SUFFIX });

        tableToUniqueKey.put(SchuelerRowBuilder.TABLE_NAME, new String[] { "ID" });

        // Zeugnisdaten
        tableToUniqueKey.put(SchulhalbjahrRowBuilder.TABLE_NAME,
                new String[] { SchulhalbjahrRowBuilder.C_JAHR, SchulhalbjahrRowBuilder.C_HALBJAHR });
        tableToUniqueKey.put(ZeugnisRowBuilder.TABLE_NAME,
                new String[] { ZeugnisRowBuilder.C_FORMULAR_ID,
                ZeugnisRowBuilder.C_KLASSE_ID, ZeugnisRowBuilder.C_SCHUELER_ID });
        tableToUniqueKey.put(ZeugnisFormularRowBuilder.TABLE_NAME, new String[] {
                ZeugnisFormularRowBuilder.C_SCHULHALBJAHR_ID,
                ZeugnisFormularRowBuilder.C_KLASSE_ID, ZeugnisFormularRowBuilder.C_BESCHREIBUNG });

        // Bewertungen
        tableToUniqueKey.put(AgBewertungRowBuilder.TABLE_NAME,
                new String[] { AgBewertungRowBuilder.C_ARBEITSGRUPPE_ID,
                AgBewertungRowBuilder.C_ZEUGNIS_ID });
        tableToUniqueKey.put(AvSvBewertungRowBuilder.TABLE_NAME, new String[] {
                AvSvBewertungRowBuilder.C_ARBEITS_UND_SOZIAL_VERHALTEN_ID,
                AvSvBewertungRowBuilder.C_ZEUGNIS_ID });
        tableToUniqueKey.put(BewertungRowBuilder.TABLE_NAME, new String[] {
                BewertungRowBuilder.C_SCHULFACH_ID, BewertungRowBuilder.C_ZEUGNIS_ID });

        tableToUniqueKey.put(BemerkungRowBuilder.TABLE_NAME, new String[] { "ID" });
        tableToUniqueKey.put(SchulamtsBemerkungRowBuilder.TABLE_NAME, new String[] { "ID" });
        tableToUniqueKey.put(SolbewertungsTextRowBuilder.TABLE_NAME, new String[] {
                SolbewertungsTextRowBuilder.C_NAME });
    }

    /**
     * Initialisiert  DBUnit.
     * @throws Exception wenn was schief geht.
     */
    @Before
    public void initDatabase() throws Exception {
        if (databaseTester == null) {
            databaseTester = new GenericDatabaseTester(dataSource, schema);
            setUpDatabaseConfig(databaseTester.getConnection().getConfig());

        }
        if (deleteDataSet == null) {
            deleteDataSet = new SzeDataSet(databaseTester.getConnection().createDataSet());
        }

        //SzeDataSet.printTableNames(databaseTester.getConnection());
    }

    /**
     * Override method to set custom properties/features
     */
    protected void setUpDatabaseConfig(DatabaseConfig config) {
        config.setProperty(DatabaseConfig.PROPERTY_BATCH_SIZE,
                Integer.valueOf(100));
        config.setProperty(DatabaseConfig.FEATURE_BATCHED_STATEMENTS,
                Boolean.TRUE);
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
            if (uk ==null) {
                throw new IllegalStateException("You must define a unique-key for each table.");
            }
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
     * Class GenericDatabaseTester.
     *
     */
    private class GenericDatabaseTester extends AbstractDatabaseTester {

        public static final String PRODUCT_H2 = "H2";
        public static final String PRODUCT_MYSQL = "MySQL";

        private final IDatabaseConnection connection;

        public GenericDatabaseTester(DataSource dataSource, String schema)
                throws DatabaseUnitException, SQLException {
            super();
            DatabaseMetaData metaData = dataSource.getConnection()
                    .getMetaData();
            final String productName = metaData.getDatabaseProductName();
            setOperationListener(new DefaultOperationListener() {
                @Override
                public void operationSetUpFinished(
                        IDatabaseConnection aConnection) {
                    // Do not invoke the "super" method to avoid that the
                    // connection is closed
                }

                @Override
                public void operationTearDownFinished(
                        IDatabaseConnection aConnection) {
                    // Do not invoke the "super" method to avoid that the
                    // connection is closed
                }
            });
            if (PRODUCT_H2.equals(productName)) {
                connection = new H2Connection(dataSource.getConnection(),
                        schema);
            } else if (PRODUCT_MYSQL.equals(productName)) {
                connection = new MySqlConnection(dataSource.getConnection(),
                        schema);
            } else {
                throw new IllegalStateException("Der Treiber " + productName
                        + " ist unbekannt.");
            }
        }

        @Override
        public IDatabaseConnection getConnection() throws Exception {
            return connection;
        }

    }




}
