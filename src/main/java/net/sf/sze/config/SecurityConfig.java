package net.sf.sze.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;

import net.sf.sze.frontend.base.URL;
import net.sf.sze.security.ActiveDirectoryAuthenticatingRealm;

import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Config for secure the application.
 *
 */
@Configuration
@PropertySource("classpath:/" + SecurityConfig.SECURITY_CONFIG)
public class SecurityConfig {

    /**
     * Enables or disable the Filter.
     */
    private final boolean enabled = true;

    /**
     * Resource which contains the configuration.
     */
    static final String SECURITY_CONFIG = "/security.properties";

    /** The url to the ldap. */
    @Value("${ldap.url}")
    private String ldapUrl;

    /** The domain used as suffix like githum.com. */
    @Value("${domain}")
    private String domain;

    /**
     * Defines the realms.
     *
     * @return a list of {@link Realm}.
     */
    private List<Realm> defineRealms() {
        final List<Realm> realms = new ArrayList<Realm>();
        final IniRealm iniRealm = new IniRealm("classpath:userAndRoles.ini");
        iniRealm.setCredentialsMatcher(new PasswordMatcher());
        setVariables();
        realms.add(new ActiveDirectoryAuthenticatingRealm(ldapUrl, domain));
        realms.add(iniRealm);
        return realms;
    }

    /**
     * Workaround, because @Value doesn't work.
     */
    private void setVariables() {
        final Properties props = new Properties();
        try {
            props.load(SecurityConfig.class.getClassLoader().getResourceAsStream(SECURITY_CONFIG));
        } catch (IOException e) {
            throw new IllegalStateException("Can't load " + SECURITY_CONFIG);
        }
        ldapUrl = props.getProperty("ldap.url");
        domain = props.getProperty("domain");

    }

    /**
     * Map urls to specific filters.
     *
     * @param filterMap a Map with existing definitions.
     *
     */
    private void defineSecurityFilter(Map<String, String> filterMap) {
        final String authAndPerms = DefaultFilter.authc.name() + ", "
                + DefaultFilter.perms.name();
        filterMap.put("/resources/**/*", DefaultFilter.anon.name());
        filterMap.put("/auth/login", DefaultFilter.anon.name());
        filterMap.put("/zeugnisMain/index", DefaultFilter.anon.name());
        filterMap.put(URL.Security.LOGOUT, DefaultFilter.logout.name());
        filterMap.put(URL.Configuration.HOME + "/**", authAndPerms + "[config:*]");
        filterMap.put(URL.Admin.HOME + "/**", authAndPerms + "[admin:*]");
        filterMap.put(URL.Zeugnis.HOME + "/**", authAndPerms + "[zeugnis:ansicht]");
        filterMap.put(URL.Bewertungen.HOME + "/**", authAndPerms + "[zeugnis:bewertung]");
        filterMap.put(URL.Bemerkung.HOME + "/**", authAndPerms + "[zeugnis:bemerkung]");
        filterMap.put("/**", DefaultFilter.authc.name());
    }

    /**
     * Init the shiro-filter bean.
     *
     * @return the shiro-filter bean.
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        final ShiroFilterFactoryBean result = new ShiroFilterFactoryBean();
        result.setSecurityManager(securityManager());
        result.setLoginUrl(URL.Security.LOGIN);
        result.setSuccessUrl(URL.Zeugnis.START);
        result.setUnauthorizedUrl(URL.Security.UNAUTHORIZED);
        result.getFilters().put(DefaultFilter.authc.name(),
                createCustomFormAuthentficationFilter());
        result.getFilters().put(DefaultFilter.perms.name(),
                createCustomPermissionsAuthorizationFilter());
        defineSecurityFilter(result.getFilterChainDefinitionMap());
        return result;
    }

    /**
     * Creates a well configured {@link FormAuthenticationFilter}.
     *
     * @return a well configured {@link FormAuthenticationFilter}.
     */
    private Filter createCustomFormAuthentficationFilter() {
        FormAuthenticationFilter authc = new FormAuthenticationFilter();
        authc.setEnabled(enabled);
        return authc;
    }

    /**
     * Creates a well configured {@link PermissionsAuthorizationFilter}.
     *
     * @return a well configured {@link PermissionsAuthorizationFilter}.
     */
    private Filter createCustomPermissionsAuthorizationFilter() {
        PermissionsAuthorizationFilter authc = new PermissionsAuthorizationFilter();
        authc.setEnabled(enabled);
        return authc;
    }

    /**
     * Makes sure the init-methods will be call. Unsure if it necessary.
     *
     * @return a {@link LifecycleBeanPostProcessor}.
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * Init the security-manager which holds the realms.
     *
     * @return the security-manager.
     */
    private org.apache.shiro.mgt.SecurityManager securityManager() {
        final DefaultWebSecurityManager securityManager =
                new DefaultWebSecurityManager();
        final ModularRealmAuthenticator mra = new ModularRealmAuthenticator();
        mra.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        securityManager.setAuthenticator(mra);
        securityManager.setRealms(defineRealms());
        return securityManager;
    }

}
