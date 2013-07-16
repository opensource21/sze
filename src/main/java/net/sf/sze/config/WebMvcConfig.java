// WebMvcConfig.java
//
// (c) SZE-Development-Team

package net.sf.sze.config;

import de.ppi.spring.mvc.formatter.NonEmptyStringAnnotationFormatterFactory;
import de.ppi.spring.mvc.oval.JPAAnnotationConfigLazy;
import de.ppi.spring.mvc.oval.MessageLookupContextRenderer;
import de.ppi.spring.mvc.oval.MessageLookupMessageValueFormatter;
import de.ppi.spring.mvc.oval.SpringMvcMessageResolver;
import de.ppi.spring.mvc.util.ApostropheEscapingPropertiesPersister;
import de.ppi.thymeleaf.bootstrap.BootstrapDialect;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.integration.spring.BeanInjectingCheckInitializationListener;
import net.sf.oval.integration.spring.SpringValidator;
import net.sf.sze.frontend.URL;

import nz.net.ultraq.web.thymeleaf.LayoutDialect;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.List;
import java.util.Properties;

/**
 * The frontend configuration for Spring.
 *
 */
@Configuration
@ComponentScan(basePackages = {"net.sf.sze.frontend",
        "net.sf.oval.integration.spring", "de.ppi.jpa.helper"})
@Import(RootConfig.class)
public class WebMvcConfig extends WebMvcConfigurationSupport {

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

    /** The Constant RESOURCES_HANDLER. */
    private static final String RESOURCES_HANDLER = "/resources/";

    /** The Constant RESOURCES_LOCATION. */
    private static final String RESOURCES_LOCATION = RESOURCES_HANDLER + "**";

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = super
                .requestMappingHandlerMapping();
        requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
        requestMappingHandlerMapping.setUseTrailingSlashMatch(true);
        return requestMappingHandlerMapping;
    }

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
        messageSource.setPropertiesPersister(
                new ApostropheEscapingPropertiesPersister());

        final Properties staticMessages = new Properties();
        staticMessages.putAll(URL.urlsAsMessages());
        staticMessages.putAll(URL.paramsAsMessages());
        staticMessages.putAll(URL.paramGroupAsMessages());
        messageSource.setCommonMessages(staticMessages);
        return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(
                RESOURCES_LOCATION);
    }

    /**
     * Create an {@link OpenEntityManagerInViewInterceptor} to follow Open
     * Session in View Patten. This isn't optimal see
     * http://heapdump.wordpress.com
     * /2010/04/04/should-i-use-open-session-in-view/ or
     * http://java.dzone.com/articles/opensessioninview-antipattern but it's
     * very common in frameworks like Grails or Play. The reason is that you
     * doesn't need so much knowledge about JPA and there is no need to write
     * tons of specific Dao-methods which make eager fetching.
     *
     * @return the {@link WebRequestInterceptor}.
     */
    @Bean
    public WebRequestInterceptor openEntityManagerInViewInterceptor() {
        return new OpenEntityManagerInViewInterceptor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(openEntityManagerInViewInterceptor());
        super.addInterceptors(registry);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableArgumentResolver resolver = new PageableArgumentResolver();

        resolver.setFallbackPagable(new PageRequest(0, FALLBACK_PAGE_SIZE));
        argumentResolvers.add(new ServletWebArgumentResolverAdapter(resolver));
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Configures the Thymeleaf view resolver.
     *
     * @return the view resolver.
     */
    @Bean
    public ThymeleafViewResolver configureInternalThymeLeafResourceViewResolver() {
        final ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setOrder(2);
        return resolver;
    }

    /**
     * Thymeleaf template engine.
     *
     * @return Thymeleaf template engine.
     */
    @Bean()
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine ste = new SpringTemplateEngine();
        ste.setTemplateResolver(templateResolver());
        ste.addDialect(new LayoutDialect());
        ste.addDialect(new BootstrapDialect());
        return ste;
    }

    /**
     * Thymeleaf template resolver.
     *
     * @return Thymeleaf template resolver.
     */
    @Bean()
    public ServletContextTemplateResolver templateResolver() {
        final ServletContextTemplateResolver resolver =
                new ServletContextTemplateResolver();
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        // TODO in Production cachable should be true.
        resolver.setCacheable(false);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    /**
     * Configures the view resolver for JSPs.
     *
     * @return the view resolver.
     */
    @Bean
    public InternalResourceViewResolver configureInternalJspResourceViewResolver() {
        final InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(1);
        resolver.setViewNames(new String[] {"xx/user/list"});
        return resolver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected org.springframework.validation.Validator getValidator() {
        final AnnotationsConfigurer annConfig = new AnnotationsConfigurer();
        annConfig.addCheckInitializationListener(
                BeanInjectingCheckInitializationListener.INSTANCE);

        final Validator ovalValidator = new Validator(annConfig,
                new JPAAnnotationConfigLazy());
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
    protected void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(
                new NonEmptyStringAnnotationFormatterFactory());
        registry.addFormatter(new DateFormatter());
        super.addFormatters(registry);
    }

    /**
     * Register a mapper so that a model entity could be found by id.
     *
     * @return a DomainClassConverter.
     */
    @Bean
    public DomainClassConverter<?> domainClassConverter() {
        return new DomainClassConverter<FormattingConversionService>(
                mvcConversionService());
    }
}
