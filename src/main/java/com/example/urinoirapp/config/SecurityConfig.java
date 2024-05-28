package com.example.urinoirapp.config;


import com.example.urinoirapp.Repository.AdminRepository;
import com.example.urinoirapp.Service.AdminService;
import com.example.urinoirapp.Service.CustomUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(AdminRepository adminRepository) {
        UserDetails admin = User.withUsername("Admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        // Add the ID to the UserDetails object
        // You can generate an ID as per your application logic
        Long id = generateId();
        User userWithId = new CustomUserDetails (id, admin.getUsername(), admin.getPassword(), admin.getAuthorities());

        // Store the user in the database


        return new InMemoryUserDetailsManager(admin);
    }

    private Long generateId() {
        // Implement your logic to generate a unique ID
        return 1L;}

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
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
                    registry.requestMatchers("/secretaires").hasRole("ADMIN");
                    registry.requestMatchers("/secretaires/new").hasRole("ADMIN");
                    registry.requestMatchers("/secretaires/edit/").hasRole("ADMIN");
                    registry.requestMatchers("/secretaires/edit/{id}").hasRole("ADMIN");
                    registry.requestMatchers("/secretaires/{id}").hasRole("ADMIN");
                    registry.requestMatchers("/users-profile/**").hasRole("ADMIN");
                    registry.requestMatchers("/profile/**").hasRole("ADMIN");
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
