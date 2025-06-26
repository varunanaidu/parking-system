package com.example.parkingsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/entry/**").hasRole("ENTRY_GATE")
            .antMatchers("/exit/**").hasRole("EXIT_GATE")
            .antMatchers("/slots/**").authenticated()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("entry")
            .password(passwordEncoder().encode("123"))
            .roles("ENTRY_GATE")
            .and()
            .withUser("exit1")
            .password(passwordEncoder().encode("123"))
            .roles("EXIT_GATE")
            .and()
            .withUser("exit2")
            .password(passwordEncoder().encode("123"))
            .roles("EXIT_GATE");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
