package net.sf.sze.checkberry.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.persistence.EntityManager;
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

    private final EntityManager entityManager;
    private final DataSource dataSource;

    private String jdbcUrl = null;


    /**
     * Initiates an object of type SzeDatabaseConnector.
     * @param entityManager der EntityManager, kann null sein.
     * @param dataSource die Datenbank.
     */
    public SzeDatabaseConnector(EntityManager entityManager,
            DataSource dataSource) {
        super();
        this.entityManager = entityManager;
        this.dataSource = dataSource;
    }

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
