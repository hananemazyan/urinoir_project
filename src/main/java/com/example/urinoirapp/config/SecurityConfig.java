package com.example.urinoirapp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(form->form
                        .loginPage("/log").permitAll())
                .authorizeHttpRequests(
                        auth->auth
                                .requestMatchers("/signup/**").permitAll()
                                .anyRequest().authenticated())
                .build();
    }
}
