package net.sf.sze.model.base;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Embedded;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import net.sf.sze.model.base.RevisionLog.Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import de.ppi.fuwesta.jpa.helper.VersionedModel;

/**
 * This class handles the revisionlog, it put's after read all values into a map
 * and use this information at write-time.
 *
 */
@Service
public class RevisionModelListener implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(
            RevisionModelListener.class);

    @PersistenceContext
    private EntityManager entityManager;

    private static EntityManager em;

    /**
     * Save the data.
     * @param model the model.
     */
    @PostLoad
    public void postLoad(RevisionModel model) {
        final Map<String, Object> map = new HashMap<String, Object>();
        putValuesToMap(map, "", model);
        model.loadedValues = map;
    }

    /**
     * Let's log a Model Removal.
     * @param model the model.
     */
    @PostRemove
    public void preRemove(RevisionModel model) {
        saveChanges(Action.DELETE, model, model.loadedValues, new HashMap<String, Object>());
    }

    /**
     * Let's log a model creation.
     * @param model the model.
     */
    @PostPersist
    public void prePersist(RevisionModel model) {
        final Map<String, Object> map = new HashMap<String, Object>();
        putValuesToMap(map, "", model);
        saveChanges(Action.CREATE, model, new HashMap<String, Object>(), map);
    }

    /**
     * Let's log a model change.
     * @param model the model.
     */
    @PreUpdate
    public void preUpdate(RevisionModel model) {
        final Map<String, Object> map = new HashMap<String, Object>();
        putValuesToMap(map, "", model);
        saveChanges(Action.UPDATE, model, model.loadedValues, map);
    }

    private void saveChanges(Action action, RevisionModel model,
            Map<String, Object> oldValues, Map<String, Object> newValues) {
        final String user = "niels";
        final String entityName = model.getClass().getSimpleName();
        final Long entityId = model.getId();
        for (Entry<String, Object> keyValue : oldValues.entrySet()) {
            final String key = keyValue.getKey();
            final Object oldValue = keyValue.getValue();
            final Object newValue = newValues.get(key);
            boolean changes = false;
            if (oldValue == null) {
                changes = newValue != null;
            } else {
                //Das funktioniert alle nicht bei Listen und bei ForeignKeys auch nicht.
                changes = !oldValue.equals(newValue);
            }
            if (changes) {
                RevisionLog revLog = new RevisionLog();
                revLog.setAction(action);
                revLog.setColumn(key);
                revLog.setEntity(entityName);
                revLog.setEntityId(entityId);
                revLog.setUser(user);
                revLog.setOldValue("" + oldValue);
                revLog.setNewValue("" + newValue);
                em.merge(revLog);
            }
        }

    }

    private void putValuesToMap(Map<String, Object> map, String prefix, Object model) {
        Class<?> clazz = model.getClass();
        while (clazz != RevisionModel.class && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {

                // Skip fields
                if (field.getAnnotation(Transient.class) != null
                        || field.getAnnotation(OneToMany.class) != null) {
                    continue;
                }
                final String fieldName = field.getName();
                final Object value = getFieldValue(field, model);
                if (field.getAnnotation(Embedded.class) != null) {
                    putValuesToMap(map, prefix + fieldName + ".", value);
                } else if (value != null && value instanceof VersionedModel) {
                    map.put(fieldName, ((VersionedModel) value).getId());
                } else if (field.getAnnotation(ManyToOne.class) != null
                        || field.getAnnotation(OneToOne.class) != null) {
                    continue;
                } else {
                    map.put(fieldName, value);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    private Object getFieldValue(Field field, Object object) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            LOG.error("Can't read " + field.getName(), e);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        em = entityManager;
    }
}

