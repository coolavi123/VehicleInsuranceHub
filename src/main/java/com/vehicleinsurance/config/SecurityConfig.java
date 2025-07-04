package com.vehicleinsurance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/dashboard/**", "/vehicles/**", "/insurances/**").hasAuthority("USER")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
            	    .loginPage("/login")
            	    .defaultSuccessUrl("/dashboard", true)
            	    .failureUrl("/login?error=true") // ADD THIS
            	    .permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .successHandler((request, response, authentication) -> {
                    authentication.getAuthorities().forEach(authority -> {
                        try {
                            if (authority.getAuthority().equals("ADMIN")) {
                                response.sendRedirect("/admin/dashboard");
                            } else {
                                response.sendRedirect("/dashboard");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                })
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .exceptionHandling(exception -> exception
            	    .accessDeniedPage("/access_denied")
            );

           


        return http.build();
    }
}
