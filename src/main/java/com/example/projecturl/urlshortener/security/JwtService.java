package com.example.projecturl.urlshortener.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey secretKey =
            Keys.hmacShaKeyFor(
                    "your-very-long-secret-key-your-very-long-secret-key".getBytes()
            );

    public String generateToken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(secretKey)
                .compact();
    }
    public String extractEmail(String token)
    {
       Claims claims =  Jwts.parser()
                .verifyWith(secretKey)
               .build()
               .parseSignedClaims(token)
               .getPayload();
        return claims.getSubject();
    }
    public boolean validateToken(String token)
    {
        try {
             Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;

        }
        catch (Exception e) {
            System.out.println("Token validation failed:");
            e.printStackTrace();
            return false;
        }

    }

}