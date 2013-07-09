// VersionHistory.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.base;

import de.ppi.jpa.helper.VersionedModel;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    public VersionHistory() {
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(final String appVersion) {
        this.appVersion = appVersion;
    }

    public Date getInstallDate() {
        return this.installDate;
    }

    public void setInstallDate(final Date installDate) {
        this.installDate = installDate;
    }
}
