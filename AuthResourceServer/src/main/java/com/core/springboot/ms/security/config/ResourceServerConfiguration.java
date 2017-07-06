package com.core.springboot.ms.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{
	
	Logger log = LoggerFactory.getLogger(ResourceServerConfiguration.class);

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //.antMatchers("/**").authenticated()
                //.antMatchers(HttpMethod.GET, "/foo").hasAuthority("FOO_READ")
                .anyRequest().authenticated();
                //.antMatchers(HttpMethod.POST, "/securedApp").hasAuthority("FOO_WRITE");
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        log.info("Configuring ResourceServerSecurityConfigurer ");
        System.out.println("******Configuring ResourceServerSecurityConfigurer********");
        resources.resourceId("foo").tokenStore(tokenStore);
    }

    @Autowired
    TokenStore tokenStore;

    @Autowired
    JwtAccessTokenConverter tokenConverter;

}
