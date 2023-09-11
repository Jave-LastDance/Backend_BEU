package com.auth.authLdap;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

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
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(ldapUrl);
        contextSource.setUserDn(ldapUsername);
        contextSource.setPassword(ldapPassword);
        contextSource.afterPropertiesSet();

        return new LdapTemplate(contextSource);
    }


}





