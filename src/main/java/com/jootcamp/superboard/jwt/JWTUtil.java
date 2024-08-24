package com.jootcamp.superboard.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {
    private final SecretKey key;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret)
    {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    //JWT 생성
    public String createJwt(String userEmail)
    {
        return Jwts.builder()
                .claim("userEmail",userEmail)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }
}
