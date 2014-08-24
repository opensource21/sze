// ServiceConfig.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring service configuration.
 *
 */
@Configuration
@ComponentScan(basePackages = {"net.sf.sze.service", "net.sf.sze.jobs", "net.sf.sze.model.base"})
@EnableScheduling
@PropertySource("classpath:/app.properties")
public class ServiceConfig {
    // For the services there are no special configurations at the moment.

}
