//SzeDbConfigurationCallback.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.checkberry.db;

import de.conceptpeople.checkerberry.db.bridge.configuration.DbConfiguration;
import de.conceptpeople.checkerberry.db.bridge.configuration.DbConfigurationCallback;


/**
 * Konfiguration der Datenbank-Informationen.
 *
 */
public class SzeDbConfigurationCallback implements DbConfigurationCallback {

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(DbConfiguration configuration) {
        // Hiermit werden DTD-Id und ihr Ort spezifiziert. Die Angabe
        // der DTD ist zwingend erforderlich.
        configuration.setDatabaseDtd("-//SZE_TEAM//DTD sze-db 1.0//EN",
                "net/sf/sze/checkberry/db/sze-db.dtd");
        // Alle JDBC-URLs verbieten
        configuration.addToAccessControlBlacklist("*");
        // Alle JDBC-URLs auf localhost Vollzuriff erlauben
        configuration.addToAccessControlWhitelist("jdbc:h2:*test*");
        // JDBC-URLs auf die Entwicklungs-DB. Lesezuriff erlauben
        configuration.addToAccessControlReadOnlyList("jdbc:h2:*dev*");
    }

}
