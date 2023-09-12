package com.authentication.authldap;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.core.ContextMapper;


import java.util.List;


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
            LdapContextSource contextSource = new LdapContextSource();
            contextSource.setUrl(ldapUrl);
            contextSource.setUserDn(ldapUsername);
            contextSource.setPassword(ldapPassword);
            contextSource.afterPropertiesSet();

            System.out.println(ldapUrl);
            System.out.println(contextSource);
            LdapTemplate LdapTemplateR = new LdapTemplate(contextSource);
            System.out.println(LdapTemplateR);
            System.out.println("Servidor LDAP ejecutando...");
            return LdapTemplateR;

        } catch (Exception e){

            e.printStackTrace();
            return null;

        }

    }

    @Autowired
    private LdapTemplate ldapTemplate;
/*
    @PostConstruct
    public void pruebaConexion() {
        try {
            Object result = ldapTemplate.search("dc=javeiana", "(uid=jalejandro_diaz)", (ContextMapper<Object>) ctx -> ctx);
            System.out.println("Conexión LDAP exitosa");
            // Trabajar con el resultado aquí si es necesario.
        } catch (Exception e) {
            // Manejar la excepción si la conexión falla.
            e.printStackTrace();
        }
    }*/






}





