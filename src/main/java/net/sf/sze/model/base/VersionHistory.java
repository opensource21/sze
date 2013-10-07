// VersionHistory.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.ppi.fuwesta.jpa.helper.VersionedModel;

/**
 * Information welche Versionen, wann genutzt wurden.
 *
 */
@Entity
@Table(name = "version_history")
public class VersionHistory extends VersionedModel implements Serializable {

    /** Die Versionsnummer. */
    @Column(name = "app_version", nullable = false, length = 255)
    private String appVersion;

    /** Datum der Installation. */
    @Column(name = "install_date", nullable = false)
    private Date installDate;

    /**
     * Gets the die Versionsnummer.
     *
     * @return the die Versionsnummer
     */
    public String getAppVersion() {
        return this.appVersion;
    }

    /**
     * Sets the die Versionsnummer.
     *
     * @param appVersion the new die Versionsnummer
     */
    public void setAppVersion(final String appVersion) {
        this.appVersion = appVersion;
    }

    /**
     * Gets the datum der Installation.
     *
     * @return the datum der Installation
     */
    public Date getInstallDate() {
        return this.installDate;
    }

    /**
     * Sets the datum der Installation.
     *
     * @param installDate the new datum der Installation
     */
    public void setInstallDate(final Date installDate) {
        this.installDate = installDate;
    }
}
