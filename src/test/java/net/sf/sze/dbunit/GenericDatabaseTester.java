//GenericDatabaseTester.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.dbunit;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.dbunit.AbstractDatabaseTester;
import org.dbunit.DatabaseUnitException;
import org.dbunit.DefaultOperationListener;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.h2.H2Connection;
import org.dbunit.ext.mysql.MySqlConnection;

/**
 * Class GenericDatabaseTester.
 *
 */
public class GenericDatabaseTester extends AbstractDatabaseTester {

    public static final String PRODUCT_H2 = "H2";
    public static final String PRODUCT_MYSQL = "MySQL";

    private final IDatabaseConnection connection;

    public GenericDatabaseTester(Connection jdbcConnection, String schema)
            throws DatabaseUnitException, SQLException {
        super();
        DatabaseMetaData metaData = jdbcConnection.getMetaData();
        final String productName = metaData.getDatabaseProductName();
        setOperationListener(new DefaultOperationListener() {
            @Override
            public void operationSetUpFinished(IDatabaseConnection aConnection) {
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
            connection = new H2Connection(jdbcConnection, schema);
        } else if (PRODUCT_MYSQL.equals(productName)) {
            connection =
                    new MySqlConnection(jdbcConnection, schema);
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
