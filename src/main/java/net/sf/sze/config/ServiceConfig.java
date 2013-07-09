package net.sf.sze.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring service configuration.
 * 
 */
@Configuration
@ComponentScan(basePackages = { "net.sf.sze.service" })
public class ServiceConfig {
    // For the services there are no special configurations at the moment.

}
