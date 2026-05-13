package com.nari.shopsphere_backend.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    
    // SECRET KEY
    private static final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkey123456";

    
    // TOKEN EXPIRATION
    private static final long EXPIRATION_TIME =
            1000 * 60 * 60 * 10;

    
    // GENERATE SECRET KEY
    private Key getSignKey() {

        byte[] keyBytes =
                SECRET_KEY.getBytes();

        return Keys.hmacShaKeyFor(
                keyBytes);
    }

    
    // GENERATE TOKEN
    public String generateToken(
            String email,
            String role) {

        return Jwts.builder()

                .setSubject(email)

                // ADD ROLE
                .claim("role", role)

                .setIssuedAt(
                        new Date(System.currentTimeMillis()))

                .setExpiration(
                        new Date(System.currentTimeMillis()
                                + EXPIRATION_TIME))

                .signWith(
                        getSignKey(),
                        SignatureAlgorithm.HS256)

                .compact();
    }

    
    // EXTRACT EMAIL
    public String extractEmail(
            String token) {

        return extractClaims(token)
                .getSubject();
    }

    
    // EXTRACT ROLE
    public String extractRole(
            String token) {

        return extractClaims(token)
                .get("role", String.class);
    }

    
    // EXTRACT CLAIMS
    private Claims extractClaims(
            String token) {

        return Jwts.parserBuilder()

                .setSigningKey(getSignKey())

                .build()

                .parseClaimsJws(token)

                .getBody();
    }

    
    // VALIDATE TOKEN
    public boolean validateToken(
            String token,
            String email) {

        String extractedEmail =
                extractEmail(token);

        return extractedEmail.equals(email)
                && !isTokenExpired(token);
    }

    
    // CHECK EXPIRATION
    private boolean isTokenExpired(
            String token) {

        return extractClaims(token)
                .getExpiration()
                .before(new Date());
    }
}