package com.zupacademy.luizpedro.microserviceproposta;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .antMatchers(HttpMethod.GET,"proposta/**")
                .hasAnyAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.GET,"proposta/**")
                .hasAnyAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.GET,"cartao/**")
                .hasAnyAuthority("SCOPE_escopo-proposta")
                .anyRequest().authenticated()).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

    }
}
