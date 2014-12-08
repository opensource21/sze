//ActiveDirectoryAuthenticatingRealm.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.security;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * {@link Realm} which allows authentication against an active directory.
 *
 */
public class ActiveDirectoryAuthenticatingRealm extends AuthenticatingRealm {

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(ActiveDirectoryAuthenticatingRealm.class);


    private final String userSuffix;

    private final Properties settings = new Properties();

    /**
     * Initiates an object of type ActiveDirectoryAuthenticatingRealm.
     * @param ldapUrl connectstring for the url like <code>ldap://localhost:389</code>.
     * @param domain used as suffix for the user for example <code>github.com</code>.
     */
    public ActiveDirectoryAuthenticatingRealm(String ldapUrl, String domain) {
        this.userSuffix = "@" + domain;
        settings.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        settings.put(Context.PROVIDER_URL, ldapUrl);
        settings.put(Context.SECURITY_AUTHENTICATION, "simple");
        settings.put("com.sun.jndi.ldap.connect.timeout", "5000");
    }



    /**
     * {@inheritDoc}
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        final String username = upToken.getUsername();
        final String password = String.valueOf(upToken.getPassword());
        LdapContext ldapContext = null;
        try {
            ldapContext = new InitialLdapContext(settings, null);
            ldapContext.addToEnvironment(Context.SECURITY_PRINCIPAL, username + userSuffix);
            ldapContext.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
            ldapContext.reconnect(null);
            return new SimpleAuthenticationInfo(username, password, getName());
        } catch (javax.naming.AuthenticationException e) {
            //All other user from other realms will pass this too
            LOG.warn("Authentication failed for user {}. {}", username,
                    e.getLocalizedMessage());
        } catch (final NamingException e) {
            LOG.error("Problem to communicate with the ldap", e);
        } finally {
            if (ldapContext != null) {
                try {
                    ldapContext.close();
                } catch (final NamingException e) {
                    LOG.error("Exception closing the ldap-context", e);
                }
            }
        }
        return null;
    }

}
