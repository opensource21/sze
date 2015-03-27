package net.sf.sze.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import de.ppi.fuwesta.thymeleaf.bootstrap2.Bootstrap2Dialect;
import de.ppi.fuwesta.thymeleaf.mail.MailToDialect;

/**
 * Configuration of Thymeleaf.
 *
 */
@Configuration
public class ThymeleafConfig {

    /**
     * Register dialect for easy bootstrap forms.
     * @return the dialect.
     */
    @Bean
    public Bootstrap2Dialect getBootstrap2Dialect() {
        return new Bootstrap2Dialect();
    }

    /**
     * Register dialect for mail-links.
     * @return the dialect.
     */
    @Bean
    public MailToDialect getMailToDialect() {
        return new MailToDialect();
    }

    /**
     * Register dialect for shiro security.
     * @return the dialect.
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
