package com.spring.configuration;

import com.spring.Providers.JWTValidationProvider;
import com.spring.Utils.JWTUtil;
import com.spring.Utils.OAuthTokenValidatorUtil;
import com.spring.filters.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JWTUtil jwtUtil;
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public SecurityConfig(JWTUtil jwtUtil,UserDetailsService userDetailsService){
        this.jwtUtil= jwtUtil;
        this.userDetailsService= userDetailsService;
    }

    @Bean
    public JWTValidationProvider jwtValidationProvider(){
        return new JWTValidationProvider(jwtUtil,userDetailsService);
    }

    @Autowired
    OAuthTokenValidatorUtil oAuthTokenValidatorUtil;



    ///  creating custom authentication provider  for jwt

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(getPasswordEncoder()); // use same encoder used while saving password
//        return authProvider;
//    }

      ///  creating custom authentication manager with provider list   for jwt

//    @Bean
//    public AuthenticationManager authenticationManager(){
//        return new ProviderManager(Arrays.asList(authenticationProvider(),jwtValidationProvider()));
//    }


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

    ///  custom security filter chain for Form Based Authentication method
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> auth
//                                .requestMatchers(new AntPathRequestMatcher("/api/registerUser")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/api/getUserById")).hasRole("USER")
//                .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session.maximumSessions(1)
//                        .maxSessionsPreventsLogin(true))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
//                .csrf(csrf -> csrf.disable())
//                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
//                .formLogin(Customizer.withDefaults());
//
//        return http.build();
//    }


    ///  custom security filter chain for Basic Authentication method

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> auth
//                        .requestMatchers(new AntPathRequestMatcher("/api/registerUser")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/api/getUserById")).hasRole("USER")
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session.maximumSessions(1)
//                        .maxSessionsPreventsLogin(true))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .csrf(csrf -> csrf.disable())
//                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }


    ///  custom security filter chain for JWT authentication method

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http,AuthenticationManager authenticationManager,JWTUtil jwtUtil) throws Exception {
//
//        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager,jwtUtil);
//        JWTValidationFilter jwtValidationFilter = new JWTValidationFilter(authenticationManager);
//        JWTRefreshFilter jwtRefreshFilter = new JWTRefreshFilter(authenticationManager,jwtUtil);
//
//
//        http.authorizeHttpRequests(auth -> auth
//                        .requestMatchers(new AntPathRequestMatcher("/api/registerUser")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/api/saveUserDetails")).hasRole("ADMIN")
//                        .anyRequest().authenticated())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .csrf(csrf -> csrf.disable())
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(jwtValidationFilter,JWTAuthenticationFilter.class)
//                .addFilterAfter(jwtRefreshFilter, JWTValidationFilter.class);
//        return http.build();
//    }


    ///  custom security filter chain for oauth2

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomOauth2SuccessHandler customOauth2SuccessHandler) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2Login(oauth -> oauth
                        .successHandler(customOauth2SuccessHandler))
                .addFilterBefore(new OAuthValidationFilter(oAuthTokenValidatorUtil), UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(Customizer.withDefaults());

        return http.build();
    }

}
