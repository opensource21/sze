// WebMvcConfig.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.config;

import java.util.List;
import java.util.Properties;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.integration.spring.SpringCheckInitializationListener;
import net.sf.oval.integration.spring.SpringValidator;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.frontend.converter.KlasseConverter;
import net.sf.sze.frontend.converter.SchulfachConverter;
import net.sf.sze.frontend.converter.ZeugnisFormularConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;

import de.ppi.fuwesta.spring.mvc.bind.ServletBindingService;
import de.ppi.fuwesta.spring.mvc.formatter.NonEmptyStringAnnotationFormatterFactory;
import de.ppi.fuwesta.spring.mvc.oval.DbCheckConfigurer;
import de.ppi.fuwesta.spring.mvc.oval.JPAAnnotationsConfigurer;
import de.ppi.fuwesta.spring.mvc.oval.MessageLookupContextRenderer;
import de.ppi.fuwesta.spring.mvc.oval.MessageLookupMessageValueFormatter;
import de.ppi.fuwesta.spring.mvc.oval.SpringMvcMessageResolver;
import de.ppi.fuwesta.spring.mvc.util.ApostropheEscapingPropertiesPersister;
import de.ppi.fuwesta.spring.mvc.util.EntityPropertiesToMessages;
import de.ppi.fuwesta.spring.mvc.util.RecursivePropertiesPersister;
import de.ppi.fuwesta.spring.mvc.util.UrlDefinitionsToMessages;

/**
 * The frontend configuration for Spring.
 *
 */
@Configuration
@ComponentScan(basePackages = {"net.sf.oval.integration.spring", "de.ppi.fuwesta.jpa.helper"})
@Import(SecurityConfig.class)
@EnableScheduling
public class WebMvcConfig extends WebMvcAutoConfigurationAdapter {

    /**
     * Page size if no other information is given.
     */
    private static final int FALLBACK_PAGE_SIZE = 5;

    /**
     * The time in seconds messages are cached.
     */
    private static final int MESSAGE_CACHE = 5;

    /** The Constant MESSAGE_SOURCE. */
    private static final String MESSAGE_SOURCE = "classpath:i18n/messages";

    /** The Constant MESSAGE_SOURCE. */
    private static final String APP_MESSAGE_SOURCE =
            "classpath:i18n/app-messages";

    /** HELP-Message. */
    private static final String HELP_MESSAGE_SOURCE =
            "classpath:i18n/help-messages";

    /** The Constant MESSAGE_SOURCE_FOR_OVAL. */
    private static final String MESSAGE_SOURCE_OVAL =
            "classpath:/net/sf/oval/Messages";

    /**
     * Die POM-Versions-Nr.
     */
    //TODO beim Test fehlt die Version
    @Value("${project.version:0.0.7}")
    private String buildNr;

    /**
     * Initiates the message resolver.
     *
     * @return a message source.
     */
    @Bean(name = "messageSource")
    public MessageSource configureMessageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(MESSAGE_SOURCE, APP_MESSAGE_SOURCE,
                HELP_MESSAGE_SOURCE, MESSAGE_SOURCE_OVAL);
        messageSource.setCacheSeconds(MESSAGE_CACHE);
        messageSource.setFallbackToSystemLocale(false);
        // Make sure Apostrophs must always be doubled..
        messageSource.setAlwaysUseMessageFormat(true);
        // This persister doubles Apostoph
        messageSource.setPropertiesPersister(new RecursivePropertiesPersister(
                new ApostropheEscapingPropertiesPersister()));

        final Class<?>[] classes = URL.class.getDeclaredClasses();
        final UrlDefinitionsToMessages urlDefinitions =
                new UrlDefinitionsToMessages(classes);
        urlDefinitions.addParamGroupAsMessages();
        urlDefinitions.addParamsAsMessages();
        urlDefinitions.addUrlsAsMessagesWithNamedParameters();
        Properties staticMessages = urlDefinitions.getMessages();
        final EntityPropertiesToMessages epm =
                new EntityPropertiesToMessages(
                        "net.sf.sze.model");
        staticMessages.putAll(epm.getProperties());
        final String version = buildNr.replace("SNAPSHOT",
                Long.toString(System.currentTimeMillis()));
        staticMessages.put("app.version", version);
        messageSource.setCommonMessages(staticMessages);
        return messageSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver =
                new PageableHandlerMethodArgumentResolver();

        resolver.setFallbackPageable(new PageRequest(0, FALLBACK_PAGE_SIZE));
        argumentResolvers.add(resolver);
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public org.springframework.validation.Validator getValidator() {
        final AnnotationsConfigurer annConfig = new AnnotationsConfigurer();
        annConfig
                .addCheckInitializationListener(SpringCheckInitializationListener.INSTANCE);
        final DbCheckConfigurer dbConfig = new DbCheckConfigurer();
        dbConfig.addCheckInitializationListener(SpringCheckInitializationListener.INSTANCE);
        final Validator ovalValidator = new Validator(annConfig, dbConfig,
                new JPAAnnotationsConfigurer(false));
        Validator.setMessageValueFormatter(
                new MessageLookupMessageValueFormatter(
                configureMessageSource()));
        Validator.setContextRenderer(new MessageLookupContextRenderer(
                configureMessageSource()));
        Validator.setMessageResolver(new SpringMvcMessageResolver(
                configureMessageSource()));
        return new SpringValidator(ovalValidator);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(
                new NonEmptyStringAnnotationFormatterFactory());
        registry.addFormatter(new DateFormatter());
        registry.addConverter(new KlasseConverter());
        registry.addConverter(new ZeugnisFormularConverter());
        registry.addConverter(new SchulfachConverter());
        super.addFormatters(registry);
    }

    /**
     * Creates a small service to bind request data to an object.
     *
     * @return the binding service.
     */
    @Bean
    public ServletBindingService servletBindingService() {
        return new ServletBindingService();
    }
}
