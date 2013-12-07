//SzeBridgeContainer.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.checkberry.db;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import de.conceptpeople.checkerberry.db.bridge.BridgeContainer;
import de.conceptpeople.checkerberry.db.bridge.configuration.DbConfigurationCallback;
import de.conceptpeople.checkerberry.db.bridge.context.DatabaseDescriptionCallback;
import de.conceptpeople.checkerberry.db.bridge.database.DatabaseConnector;
import de.conceptpeople.checkerberry.db.bridge.resource.ClasspathResourceLoader;
import de.conceptpeople.checkerberry.db.spring.bridge.hibernate.resource.SpringClasspathResourceLoader;


/**
 * Anbindungscontainer für die Checkerberry, so ist klar welche Klassen
 * genommen werden.
 *
 */
public class SzeBridgeContainer implements BridgeContainer, ResourceLoaderAware {

    private static final SpringClasspathResourceLoader CLASSPATH_RESOURCE_LOADER
            = new SpringClasspathResourceLoader();

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private DataSource dataSource;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
      CLASSPATH_RESOURCE_LOADER.setResourceLoader(resourceLoader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DbConfigurationCallback getConfigurationCallback() {
        return new SzeDbConfigurationCallback();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabaseConnector getDatabaseConnector() {
        return new SzeDatabaseConnector(entityManager, dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabaseDescriptionCallback getDatabaseDescriptionCallback() {
        return new SzeDatabaseDescriptionCallback();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClasspathResourceLoader getResourceLoader() {
        return CLASSPATH_RESOURCE_LOADER;
    }


}
