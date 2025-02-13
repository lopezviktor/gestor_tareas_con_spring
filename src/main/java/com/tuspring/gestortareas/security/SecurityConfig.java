package com.tuspring.gestortareas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())  // Desactiva CSRF (solo si es API REST)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/tareas/**", "/proyectos/**", "/styles/**").permitAll() // Permitir acceso a estas rutas
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }
}