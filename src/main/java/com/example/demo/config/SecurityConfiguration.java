package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        String[] SWAGGER_WHITELIST = {
                "/swagger-ui.html",
                "/swagger-ui/*",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/webjars/**"
        };
        httpSecurity.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/person/**").permitAll()
                .antMatchers(SWAGGER_WHITELIST).permitAll()
                .anyRequest().authenticated();
    }
}
