package com.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(getPasswordEncoder()); // use same encoder used while saving password
        return authProvider;
    }

//    @Bean
//    public UserDetailsService getUserDetailsService(){
//        UserDetails userDetails1 = User.withUsername("customName1")
//                .password("{noop}password1")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails userDetails2 = User.withUsername("customName2")
//                .password(new BCryptPasswordEncoder().encode("password2"))
//                .roles("ADMIN")
//                .build();ßßßßß
//
//
//        return  new InMemoryUserDetailsManager(userDetails1,userDetails2);
//
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                                .requestMatchers(new AntPathRequestMatcher("/api/registerUser")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/getUserById")).hasRole("USER")
                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}
