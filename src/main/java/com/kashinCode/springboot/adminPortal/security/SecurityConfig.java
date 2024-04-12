package com.kashinCode.springboot.adminPortal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails kashin = User.builder()
                .username("kashin")
                .password("{noop}kashin")
                .roles("DEV","MANAGER","ADMIN")
                .build();

        UserDetails batman = User.builder()
                .username("batman")
                .password("{noop}batman")
                .build();

       return new InMemoryUserDetailsManager(kashin,batman);
    }
}
