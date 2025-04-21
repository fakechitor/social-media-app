package com.fakechitor.socialmediapostservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {


    private final String secretToken;

    public JwtUtils(@Value("${jwt.key}") String secretToken) {
        this.secretToken = secretToken;
    }

    public String getUsernameFromJwt(String token) {

        return extractAllClaimsFromJwt(token).getSubject();
    }

    public Integer getUserIdFromJwt(String token) {
        return extractAllClaimsFromJwt(token).get("userId", Integer.class);
    }

    private Claims extractAllClaimsFromJwt(String token) {
        return Jwts.parser()
                .verifyWith(parseStringToSecretToken(secretToken))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey parseStringToSecretToken(String secretToken) {
        return Keys.hmacShaKeyFor(secretToken.getBytes());
    }
}
