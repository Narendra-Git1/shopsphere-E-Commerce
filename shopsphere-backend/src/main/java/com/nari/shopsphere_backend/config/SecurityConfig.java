package com.nari.shopsphere_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nari.shopsphere_backend.jwt.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    
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

            // DISABLE CSRF
            .csrf(csrf -> csrf.disable())

            // STATELESS SESSION
            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS))

            // API AUTHORIZATION
            .authorizeHttpRequests(auth -> auth

                    // PUBLIC APIs
                    .requestMatchers(

                            "/api/auth/**",
                            "/swagger-ui/**",
                            "/v3/api-docs/**"

                    ).permitAll()

                    // ADMIN APIs
                    .requestMatchers(

                            "/api/admin/**"

                    ).hasRole("ADMIN")

                    // ALL OTHER APIs
                    .anyRequest().authenticated()
            )

            // ADD JWT FILTER
            .addFilterBefore(

                    jwtAuthenticationFilter,

                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}