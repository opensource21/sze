//SzeServer.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Server zum Starten von SZE.
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SzeServer {


    /**
     * Main-Methode zum Starten des Servers.
     * @param args start-argumente.
     */
    public static void main(String[] args) {
        SpringApplication.run(SzeServer.class, args);

    }

}
