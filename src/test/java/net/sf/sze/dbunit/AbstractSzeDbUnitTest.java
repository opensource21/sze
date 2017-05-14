//AbstractSzeDbUnitTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.dbunit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import net.sf.sze.SzeServer;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.dbunit.dataset.IDataSet;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


/**
 * Class AbstractSzeDbUnitTest
 *
 */
@SpringBootTest(classes = SzeServer.class, webEnvironment=WebEnvironment.RANDOM_PORT)
public abstract class AbstractSzeDbUnitTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private DataSource dataSource;

    /**
     * DB-Schema.
     */
    @Value("${spring.datasource.schema}")
    private String schema;

    private SzeDatabase szeDatabase;


    @Before
    public void setUpSecurity() {
        SecurityManager securityManger = mock(SecurityManager.class, Mockito.RETURNS_DEEP_STUBS);
//        securityManger.
        ThreadContext.bind(securityManger);
        Subject subject = SecurityUtils.getSubject();
        when(subject.getPrincipal()).thenReturn("JUnitTest");
    }

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


    @Rule
    public SzeDatabase getSzeDatabase() throws SQLException {
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
