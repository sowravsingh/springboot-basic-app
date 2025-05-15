package com.spring.filters;

import com.spring.DTO.JwtAuthenticationToken;
import com.spring.Utils.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTRefreshFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    public JWTRefreshFilter(AuthenticationManager authenticationManager,JWTUtil jwtUtil){
        this.authenticationManager =authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (!request.getServletPath().equals("/refresh-token")){
            filterChain.doFilter(request,response);
            return;
        }

        String refreshToken  = extractRefreshToken(request);

        if (refreshToken ==null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(refreshToken);

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if(authenticate.isAuthenticated()){
            String newAccessToken = jwtUtil.generateToken(authenticate.getName(),15);
            response.setHeader("Authorization","Bearer "+newAccessToken);
        }


    }


    private  String extractRefreshToken(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();

        if (cookies ==null){
            return null;
        }

        String refreshToken =null;

        for (Cookie cookie :cookies){

            if ("refreshToken".equals(cookie.getName())){
                refreshToken = cookie.getValue();
            }
        }

        return refreshToken;
    }

}
