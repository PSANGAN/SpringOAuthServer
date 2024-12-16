package com.pcgs.spring.poc.authorization.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class EndpointSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.httpBasic()
                .and()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and().build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDtlSvc = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("bill")
                    .password(passwordEncoder().encode("12345"))
                    .authorities("read").build();

        userDtlSvc.createUser(user1);

        return userDtlSvc;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }
}
