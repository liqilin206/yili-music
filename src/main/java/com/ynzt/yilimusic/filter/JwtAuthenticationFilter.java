package com.ynzt.yilimusic.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynzt.yilimusic.config.SecurityConfig_Ex;
import com.ynzt.yilimusic.entity.User;
import com.ynzt.yilimusic.exception.BizException;
import com.ynzt.yilimusic.exception.ExceptionType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            User user = new ObjectMapper()
                    .readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           user.getUsername(),
                           user.getPassword(),
                           new ArrayList<>()
                   )
            );

        } catch (Exception e) {
            throw new BizException(ExceptionType.FORBIDDEN);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject( ((User)authResult.getPrincipal()).getUsername() )
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig_Ex.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConfig_Ex.SECRET.getBytes()))

                ;
        response.addHeader(SecurityConfig_Ex.HEADER_STRING, token);
    }
}
