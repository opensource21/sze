//TomcatConfig.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of tomcat.
 *
 */
@Configuration
public class TomcatConfig {

    @Value("${alternativePort:0}")
    private int alternativePort;

    /**
     * Initialize the embedded tomcat.
     * @return the container.
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat =
                new TomcatEmbeddedServletContainerFactory();
        if (alternativePort > 0) {
            final Connector connector =
                    new Connector("org.apache.coyote.http11.Http11NioProtocol");
            connector.setPort(alternativePort);
            tomcat.addAdditionalTomcatConnectors(connector);
        }
        return tomcat;
    }

}
