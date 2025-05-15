package com.spring.filters;

import com.spring.DTO.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTValidationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    public JWTValidationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager= authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = extractJWTTokenFromRequest(request);

        if(jwtToken != null){
            JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(jwtToken);
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            if(authenticate.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            }

        }

        filterChain.doFilter(request,response);

    }


    public String extractJWTTokenFromRequest(HttpServletRequest request){
        String bearerToken  = request.getHeader("Authorization");

        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }

        return null;
    }
}
