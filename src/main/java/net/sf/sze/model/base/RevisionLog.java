//RevisionLog.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.model.base;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * RevisionLog-entity.
 *
 */
@Entity
public class RevisionLog {

    /**
     *
     * Possible actions on a table.
     *
     */
    public enum Action {
        /**Create.*/
        CREATE,
        /**Update.*/
        UPDATE,
        /**Delete.*/
        DELETE;
    }

    /**
     * The identifier of the entity.
     */
    @Id
    @GeneratedValue()
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Timestamp modificationtime = new Timestamp(System.currentTimeMillis());

    @Column(nullable = false, length = 20)
    private String user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Action action;

    @Column(nullable = false, length = 50)
    private String entity;

    @Column(nullable = false, length = 250)
    private String column;

    @Column(nullable = false)
    private Long entityId;

    @Column(length = 1000)
    private String oldValue;

    @Column(length = 1000)
    private String newValue;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the modificationtime
     */
    public Timestamp getModificationtime() {
        return modificationtime;
    }

    /**
     * @param modificationtime the modificationtime to set
     */
    public void setModificationtime(Timestamp modificationtime) {
        this.modificationtime = modificationtime;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * @return the entity
     */
    public String getEntity() {
        return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }

    /**
     * @return the column
     */
    public String getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(String column) {
        this.column = column;
    }

    /**
     * @return the entityId
     */
    public Long getEntityId() {
        return entityId;
    }

    /**
     * @param entityId the entityId to set
     */
    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    /**
     * @return the oldValue
     */
    public String getOldValue() {
        return oldValue;
    }

    /**
     * @param oldValue the oldValue to set
     */
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * @return the newValue
     */
    public String getNewValue() {
        return newValue;
    }

    /**
     * @param newValue the newValue to set
     */
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

}
