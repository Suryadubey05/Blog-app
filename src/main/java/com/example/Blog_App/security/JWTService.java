package com.example.Blog_App.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    private static final String JWT_KEY = "ni3em83ue47yr6nc43y487rb1cr3y8783ybc8e23ejf8r6nrf";
    private final Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);

    public String createJwt(Long userId){
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
                // .withExpiresAt() //TODO: setup and expiry parameter
                .sign(algorithm);
    }

    public Long retrieveUserId(String jwtString){
        var decodeJWT = JWT.decode(jwtString);
        var userId = Long.valueOf(decodeJWT.getSubject());
        return userId;
    }



}
