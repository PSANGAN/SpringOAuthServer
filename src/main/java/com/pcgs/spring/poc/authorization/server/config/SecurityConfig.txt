package com.pcgs.spring.poc.authorization.server.config;

import com.pcgs.spring.poc.authorization.server.filters.CustomAuthenticationFilter;
import com.pcgs.spring.poc.authorization.server.repositories.UserRepository;
import com.pcgs.spring.poc.authorization.server.services.JpaUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
public class SecurityConfig {

    // We can skip this,
    // bcs we are implemented @Service JpaUserDetailsService class which
    // implements UserDetailsService interface, so framework wire up
//    private final UserRepository repository;
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new JpaUserDetailsService(repository);
//    }

    private final CustomAuthenticationFilter customAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated()  // don't worry about this
                .and().build();
    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance(); // should not use this in a prod app => BCryptPasswordEncoder
//    }
}