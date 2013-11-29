//TestConfig.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.checkberry.db;

import net.sf.sze.config.RootConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import de.conceptpeople.checkerberry.db.spring.jpa.bridge.resource.SpringClasspathResourceLoader;


/**
 * Spring Config für den Test.
 *
 */
@Configuration
@Import(RootConfig.class)
public class TestConfig {

    /**
     * Liefert einen Resourceloader damit die Testdaten geladen werden können.
     * @return einen Resourceloader damit die Testdaten geladen werden können.
     */
    public SpringClasspathResourceLoader resourceLoader() {
        return new SpringClasspathResourceLoader();
    }


}
