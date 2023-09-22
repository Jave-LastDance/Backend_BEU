package com.authentication.authldap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPSearchException;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;
import com.unboundid.util.ssl.SSLUtil;
import com.unboundid.util.ssl.TrustAllTrustManager;

import javax.net.ssl.SSLSocketFactory;
import java.util.ResourceBundle;

@Configuration
public class SecurityConfig {

    @Value("${spring.ldap.urls}")
    private String ldapUrl;

    @Value("${spring.ldap.username}")
    private String ldapUsername;

    @Value("${spring.ldap.password}")
    private String ldapPassword;

    @Bean
    public LdapTemplate ldapTemplate() {
        try {
            // Configure the LDAP context source
            LdapContextSource contextSource = new LdapContextSource();
            contextSource.setUrl(ldapUrl);
            contextSource.setUserDn(ldapUsername);
            contextSource.setPassword(ldapPassword);
            contextSource.afterPropertiesSet();

            // Create the LdapTemplate object
            LdapTemplate ldapTemplate = new LdapTemplate(contextSource);

            try {
                Login();
            } catch (LDAPException e) {
                throw new RuntimeException(e);
            }

            // Perform an LDAP authentication test
            boolean isAuthenticated = authenticateUser(ldapTemplate, "cn=jalejandro_diaz", "MaGister2021Diaz");

            if (isAuthenticated) {
                System.out.println("LDAP authentication successful.");
            } else {
                System.out.println("LDAP authentication failed.");
            }

            return ldapTemplate;
        } catch (Exception e) {
            // Handle the exception appropriately, this is just an example
            throw new RuntimeException("Error configuring LDAP connection", e);
        }
    }


    public static LDAPConnection Login() throws LDAPException {
        //ResourceBundle rb = ResourceBundle.getBundle("javedirpru.javeriana.edu.co");
        String server = "javedirpru.javeriana.edu.co";
        String user = "uid=be-u,o=javeriana.edu.co,o=edu";
        // o=javeriana.edu.co,o=edu".replaceAll("#","cestepa");
        String pwd = "EJNR+2g-r]7PXRX";
        int puerto = Integer.parseInt("636");
        boolean isSSL = false;
        LDAPConnection con = null;
        try {
            if (isSSL) {
                System.out.println("Se creo conexion LDAP SSL...");
                SSLUtil sslUtil = new SSLUtil(new TrustAllTrustManager());
                SSLSocketFactory sslSocketFactory = sslUtil.createSSLSocketFactory();
                con = new LDAPConnection(sslSocketFactory, server, puerto, user, pwd);
            } else {
                System.out.println("Se creo conexion LDAP sin SSL...");
                con = new LDAPConnection(server, puerto, user, pwd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de conexion a LDAP");
        }
        return con;
    }




    private boolean authenticateUser(LdapTemplate ldapTemplate, String userDn, String password) {
        try {
            // Attempt to bind to the LDAP server with the user's DN and password
            ldapTemplate.getContextSource().getContext(userDn, password);
            return true; // Authentication was successful
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Authentication failed
        }
    }
}
