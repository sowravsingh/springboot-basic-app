package com.spring.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.DTO.LoginRequest;
import com.spring.Utils.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;


    public  JWTAuthenticationFilter(AuthenticationManager authenticationManager,JWTUtil jwtUtil){

        this.jwtUtil =jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(!request.getServletPath().equals("/generate-token")){
            filterChain.doFilter(request,response);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequest loginRequest= objectMapper.readValue(request.getInputStream(), LoginRequest.class);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authToken);

        if(authenticate.isAuthenticated()){
            String token = jwtUtil.generateToken(authenticate.getName(),15);
            response.setHeader("Authorization","Bearer "+token);

            String refreshToken = jwtUtil.generateToken(authenticate.getName(),7*24*60);

            Cookie refreshCookie = new Cookie("refreshToken",refreshToken);
            refreshCookie.setHttpOnly(true);
            refreshCookie.setSecure(true);
            refreshCookie.setPath("/refresh-token");
            refreshCookie.setMaxAge(24*60*7);

            response.addCookie(refreshCookie);
        }
    }
}
