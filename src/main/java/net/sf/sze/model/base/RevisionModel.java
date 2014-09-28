//AuditableModel.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.model.base;

import java.util.Map;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import de.ppi.fuwesta.jpa.helper.VersionedModel;


/**
 * Base-class for all models which must be revision safe.
 *
 */
@MappedSuperclass
@EntityListeners(RevisionModelListener.class)
public class RevisionModel extends VersionedModel {

    /**
     * Map which stores the original values.
     */
    @Transient
    private Map<String, Object> loadedValues;

    /**
     * Returns the loaded Values.
     * @return the loadedValues.
     */
    Map<String, Object> getLoadedValues() {
        return loadedValues;
    }

    /**
     * Set the loaded values.
     * @param loadedValues the loadedValues to set.
     */
    void setLoadedValues(Map<String, Object> loadedValues) {
        this.loadedValues = loadedValues;
    }


}
