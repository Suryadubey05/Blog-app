package com.example.Blog_App.security;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class JWTAuthenticationFilter extends AuthenticationFilter {

    public JWTAuthenticationFilter() {
        super(new JWTAuthenticationManager(), new JWTAuthenticationConverter());
    }

}
