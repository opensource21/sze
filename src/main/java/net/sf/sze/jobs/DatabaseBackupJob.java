//DatabaseBackup.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.jobs;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Job der sicherstellt, dass die Datenbank regelmäßig gesichert wird.
 *
 */
@Component
public class DatabaseBackupJob {

    @Resource
    private DataSource dataSource;

    @Resource
    private Environment env;

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(DatabaseBackupJob.class);


    /**
     * Sicherung einer H2-Datenbank.
     */
    @Scheduled(cron = "${cron.dbBackup}")//
    public void backupH2Database() {
        if (env.getProperty("spring.datasource.driver-class-name").equals("org.h2.Driver")) {
            final String backupDir = env.getProperty("backupDir", "databases/backup");
             final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
             final File backupFile = new File(backupDir, "sze_"
                     + dateFormat.format(new Date()) + ".zip");
             final String backupCmd = "BACKUP TO '" + backupFile.getAbsolutePath() + "'";
             LOG.info("Backup to {}", backupFile.getAbsolutePath());
             try (Connection conn = dataSource.getConnection()) {
                 conn.createStatement().execute(backupCmd);
             } catch (SQLException sqlE) {
                 LOG.error("Couldn't create Database-Backup!", sqlE);
             }
        }

    }
}
