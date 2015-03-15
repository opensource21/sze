//AbstractSzeDbUnitTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.dbunit;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import net.sf.sze.SzeServer;

import org.dbunit.dataset.IDataSet;
import org.junit.AfterClass;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * Class AbstractSzeDbUnitTest
 *
 */
@SpringApplicationConfiguration(classes = SzeServer.class)
@WebAppConfiguration
public abstract class AbstractSzeDbUnitTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private DataSource dataSource;

    /**
     * DB-Schema.
     */
    @Value("${spring.datasource.schema}")
    private String schema;

    private SzeDatabase szeDatabase;


    /**
     * @param dataSet
     * @throws Exception
     * @see net.sf.sze.dbunit.SzeDatabase#cleanlyInsert(org.dbunit.dataset.IDataSet)
     */
    public void cleanlyInsert(IDataSet dataSet) throws Exception {
        szeDatabase.cleanlyInsert(dataSet);
    }


    /**
     * @param expected
     * @throws Exception
     * @see net.sf.sze.dbunit.SzeDatabase#checkResult(org.dbunit.dataset.IDataSet)
     */
    public void checkResult(IDataSet expected) throws Exception {
        szeDatabase.checkResult(expected);
    }


    /**
     * @throws Exception
     * @see net.sf.sze.dbunit.SzeDatabase#generateRowBuilder()
     */
    public void generateRowBuilder() throws Exception {
        szeDatabase.generateRowBuilder();
    }


    @Rule
    public SzeDatabase getSzeDatebase() throws SQLException {
        if (szeDatabase == null) {
            szeDatabase = new SzeDatabase(dataSource.getConnection(), schema);
        }
        return szeDatabase;
    }

    @AfterClass
    public static void closeDB() throws Exception {
        SzeDatabase.destroyDatabase();
    }


}
