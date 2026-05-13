package com.nari.shopsphere_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    
    // PASSWORD ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    
    // SECURITY CONFIGURATION
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        
        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                    
                // PUBLIC APIs
                .requestMatchers(

                        "/api/auth/**",

                        "/swagger-ui/**",

                        "/v3/api-docs/**"

                ).permitAll()

                    
                // SECURE ALL OTHER APIs
                .anyRequest().authenticated()
            );

        return http.build();
    }
}