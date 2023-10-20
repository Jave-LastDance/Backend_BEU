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

import javax.naming.Context;
import javax.naming.directory.InitialDirContext;
import javax.net.ssl.SSLSocketFactory;
import java.util.*;

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
                LDAPConnection con;
                con = Login();

                System.out.println("Conexion: " + con);
            } catch (LDAPException e) {
                throw new RuntimeException(e);
            }
            return ldapTemplate;
        } catch (Exception e) {
            // Handle the exception appropriately, this is just an example
            throw new RuntimeException("Error configuring LDAP connection", e);
        }
    }


    public static LDAPConnection Login() throws LDAPException {

        String server = "javedirpru.javeriana.edu.co";
        String user = "uid=be-u, ou=pseudoAccounts, o=javeriana.edu.co, o=edu";
        String pwd = "EJNR+2g-r]7PXRX";
        int puerto = Integer.parseInt("636");
        boolean isSSL = true;
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

    public ResponseServiceBus consultarCuenta(String cuenta, String pass) {

        ResourceBundle properties = ResourceBundle.getBundle("puj.edu.co.ss.util.config");
        ResponseServiceBus persona = new ResponseServiceBus();
        ResponseService userInfo = new ResponseService();

        try {
            {
                Properties authEnv = new Properties();
                authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
                authEnv.put(Context.PROVIDER_URL, properties.getString("ldap_serverUser"));
                authEnv.put(Context.SECURITY_PRINCIPAL,
                        properties.getString("ldap_usuario").replace(":usuario", cuenta));
                authEnv.put(Context.SECURITY_CREDENTIALS, pass);
                new InitialDirContext(authEnv);
            }

            LDAPConnection con = this.Login();
            String attributesArrayG[] = {"cn", "uniqueMember"};
            Map<String, String[]> grupos = new HashMap<String, String[]>();// gruposStr.split(";");
            SearchRequest searchRequestG = new SearchRequest(properties.getString("ldap_baseGroups"), SearchScope.SUB,
                    properties.getString("ldap_groupsFilter"));
            searchRequestG.setAttributes(Arrays.asList(attributesArrayG));
            SearchResult searchResultG = con.search(searchRequestG);

            for (SearchResultEntry entry : searchResultG.getSearchEntries()) {
                String gcn = entry.getAttributeValue("cn");
                String[] uniqueMembers = entry.getAttributeValues("uniqueMember");
                grupos.put(gcn, uniqueMembers);

            }

            String attributesArray[] = {"uid", "givenName", "businessCategory", "emplid", "pwdChangedTime",
                    "apellido1", "apellido2", "programa", "dependencia", "employeeNumber"};

            List<String> attributes = Arrays.asList(attributesArray);

            String filter = "(uid=" + cuenta + ")";

            SearchRequest searchRequest = new SearchRequest(properties.getString("ldap_baseUsers"), SearchScope.SUB, filter);
            searchRequest.setAttributes(attributes);
            SearchResult searchResult;


            try {
                searchResult = con.search(searchRequest);
                if (searchResult.getEntryCount() != 1) {
                    persona.setValidacion(false);
                    throw new Exception(
                            "La consulta " + filter + " devolvio " + searchResult.getEntryCount() + " registros");

                }

                persona.setValidacion(true);


                for (SearchResultEntry entry : searchResult.getSearchEntries()) {
                    String givenName = entry.getAttributeValue("givenName");
                    String emplid = entry.getAttributeValue("emplid");
                    String pwdChangedTime = entry.getAttributeValue("pwdChangedTime");
                    String programa = entry.getAttributeValue("programa");
                    String dependecia = entry.getAttributeValue("dependencia");
                    String employeeNumber = entry.getAttributeValue("employeeNumber");
                    String apellidos = entry.getAttributeValue("apellido1") + " " + entry.getAttributeValue("apellido2");
                    String[] roles = entry.getAttributeValues("businessCategory");

                    if (givenName == null) {
                        givenName = "";
                    }
                    int pwdLifetime = 1000;
                    Collection<String> tempRol = new ArrayList<String>();
                    for (String tempS : roles)
                        tempRol.add(tempS);
                    userInfo.setOprId(cuenta);
                    userInfo.setNombre(givenName);
                    userInfo.setApellidos(apellidos);
                    userInfo.setDependencia(dependecia);
                    userInfo.setEmplId(emplid);
                    userInfo.setDiasVencimiento(String.valueOf(pwdLifetime));
                    userInfo.setPrograma(programa);
                    userInfo.setDocumento(employeeNumber);
                    userInfo.setRoles(tempRol);
                    persona.setPersona(userInfo);

                }

            } catch (LDAPSearchException lse) {
                this.logout(con);
                lse.printStackTrace();
            } finally {
                this.logout(con);
            }

        } catch (Exception e) {
            System.out.println("Ocurrio un error al consultar el usuario: " + cuenta + ". Error: " + e.getMessage());
        }

        return persona;

    }


}
