// VersionedModel.java
//
// (c) SZE-Development-Team

/**
 *
 */
package net.sf.sze.model.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Basic Model which contains the id and version.
 * @author niels
 *
 */
@MappedSuperclass
public class VersionedModel {

    @Transient
    private transient boolean optimisiticLockingViolated = false;

    @Id
    @GeneratedValue()
    @Column(unique = true, nullable = false)
    private Long id;


    @Version
    @Column(nullable = false)
    private Long version;


    public Long getId() {
        return id;
    }


    public Long getVersion() {
        return version;
    }

    public void setVersion(final Long newVersion) {
        if (newVersion != null) {
            if ((version != null) && (version.longValue() > newVersion
                    .longValue())) {
                optimisiticLockingViolated = true;
            } else {
                version = newVersion;
            }
        }
    }
}
