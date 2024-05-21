package com.example.urinoirapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("Mouhamed")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }


    @Primary
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/Static/css/**","/images/**").permitAll();
                    registry.requestMatchers("/medecins/**").hasRole("ADMIN");
                    registry.requestMatchers("/medecins/edit").hasRole("ADMIN");
                    registry.requestMatchers("/medecins/edit/{id}").hasRole("ADMIN");
                    registry.requestMatchers("/medecins/{id}").hasRole("ADMIN");
                    registry. requestMatchers ( "/secretaires" ).hasRole("ADMIN");
                    registry.requestMatchers ( "/secretaires/new" ).hasRole("ADMIN");
                    registry.requestMatchers ( "/secretaires/edit/" ).hasRole("ADMIN");
                    registry.requestMatchers ( "/secretaires/edit/{id}" ).hasRole("ADMIN");
                    registry.requestMatchers ( "/secretaires/{id}" ).hasRole("ADMIN");

                    registry.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                );

        return httpSecurity.build();
    }
}
