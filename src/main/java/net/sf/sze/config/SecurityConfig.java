package net.sf.sze.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import net.sf.sze.frontend.base.URL;
import net.sf.sze.security.ActiveDirectoryAuthenticatingRealm;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config for secure the application.
 *
 */
@Configuration
public class SecurityConfig {

    /**
     * Enables or disable the Filter.
     */
    private final boolean enabled = true;

    /**
     * Defines the realms.
     *
     * @param ldapUrl the url to the Ldap-Server
     * @param ldapDomain the domain of the Ldap-Server
     * @param userAndRoleConfig location of the userAndRoleConfig (classpath:, file:)
     *
     * @see IniRealm#IniRealm(String).
     *
     * @return a list of {@link Realm}.
     */
    private List<Realm> defineRealms(String ldapUrl, String ldapDomain, String userAndRoleConfig) {
        final List<Realm> realms = new ArrayList<Realm>();
        final IniRealm iniRealm = new IniRealm(userAndRoleConfig);
        iniRealm.setCredentialsMatcher(new PasswordMatcher());
        if (StringUtils.isNotEmpty(ldapUrl) && StringUtils.isNotEmpty(ldapDomain)) {
            realms.add(new ActiveDirectoryAuthenticatingRealm(ldapUrl, ldapDomain));
        }
        realms.add(iniRealm);
        return realms;
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
        filterMap.put("/css/**/*", DefaultFilter.anon.name());
        filterMap.put("/js/**/*", DefaultFilter.anon.name());
        filterMap.put("/images/**/*", DefaultFilter.anon.name());
        filterMap.put("/img/**/*", DefaultFilter.anon.name());
        filterMap.put("/html5_error.html", DefaultFilter.anon.name());
        filterMap.put("/auth/login", DefaultFilter.anon.name());
        filterMap.put("/zeugnisMain/index", DefaultFilter.anon.name());
        filterMap.put(URL.Security.LOGOUT, DefaultFilter.logout.name());
        filterMap.put(URL.Configuration.HOME + "/**", authAndPerms + "[config:*]");
        filterMap.put(URL.Admin.HOME + "/**", authAndPerms + "[admin:*]");
        filterMap.put(URL.Zeugnis.HOME + "/**", authAndPerms + "[zeugnis:ansicht]");
        filterMap.put(URL.Bewertungen.HOME + "/**", authAndPerms + "[zeugnis:bewertung]");
        filterMap.put(URL.Bemerkung.HOME + "/**", authAndPerms + "[zeugnis:bemerkung]");
        filterMap.put(URL.H2CONSOLE + "/**", authAndPerms + "[admin:h2console]");
        filterMap.put("/**", DefaultFilter.authc.name());
    }

    /**
     * Init the shiro-filter bean.
     * @param ldapUrl the url to the Ldap-Server
     * @param ldapDomain the domain of the Ldap-Server
     * @param userAndRoleConfig location of the userAndRoleConfig (classpath:, file:)
     *
     * @return the shiro-filter bean.
     */
    @Bean
    @Autowired
    public ShiroFilterFactoryBean shiroFilter(@Value("${shiro.ldap.url}") String ldapUrl,
            @Value("${shiro.ldap.domain}") String ldapDomain,
            @Value("${shiro.userAndRole}") String userAndRoleConfig) {
        final ShiroFilterFactoryBean result = new ShiroFilterFactoryBean();
        result.setSecurityManager(securityManager(ldapUrl, ldapDomain, userAndRoleConfig));
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
     * @param ldapUrl the url to the Ldap-Server
     * @param ldapDomain the domain of the Ldap-Server
     * @param userAndRoleConfig location of the userAndRoleConfig (classpath:, file:)
     *
     * @return the security-manager.
     */
    private org.apache.shiro.mgt.SecurityManager securityManager(String ldapUrl,
            String ldapDomain, String userAndRoleConfig) {
        final DefaultWebSecurityManager securityManager =
                new DefaultWebSecurityManager();
        final ModularRealmAuthenticator mra = new ModularRealmAuthenticator();
        mra.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        securityManager.setAuthenticator(mra);
        securityManager.setRealms(defineRealms(ldapUrl, ldapDomain, userAndRoleConfig));
        return securityManager;
    }

}
