package net.sf.sze.checkberry.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.conceptpeople.checkerberry.db.bridge.database.DatabaseConnector;

/**
 * Datenbank-Connector, der auf Spring und JPA basiert.
 */
public class SzeDatabaseConnector implements DatabaseConnector {

    /**
     * Log-Instanz.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            SzeDatabaseConnector.class);

    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private DataSource dataSource;
    private String jdbcUrl = null;



    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void createDatabaseSchema() {
        final Map<String, Object> properties = entityManager.getProperties();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Properties of entity manager: " + properties.toString());
        }
    }

    @Override
    public String getDatabaseSchema() {
        return null;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public String getUrl() {
        if (jdbcUrl == null) {
            Connection con = null;
            try {
                con = dataSource.getConnection();
                jdbcUrl = con.getMetaData().getURL();
            } catch (SQLException e) {
                LOG.error("SQLException getting datasource connection: " + e.getMessage());
                jdbcUrl = null;
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e1) {
                        // IGNORE
                    }
                }
            }
        }
        return jdbcUrl;
    }

}
