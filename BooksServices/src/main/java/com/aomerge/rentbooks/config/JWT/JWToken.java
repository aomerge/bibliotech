package com.aomerge.rentbooks.config.JWT;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class JWToken {
    private static final String KEY_CREATE = System.getenv("JWT_CREATE_KEY");
    private static final Key key_create = Keys.hmacShaKeyFor(KEY_CREATE.getBytes());
    private List<String> Logger;

    private static boolean ValidateToken(String token, Key key, Object Data) {
        try {
            Jwts.parser().setSigningKey(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private static Claims GetClaims(String token, Key key) {
        return Jwts.parser().setSigningKey(key).build().parseSignedClaims(token).getBody();
    }

    private static String GenerateToken(String subject, int expirationTime) {
        String jti = UUID.randomUUID().toString();
        Date now = new Date();
        Date exp = new Date(now.getTime() + 60000L * expirationTime);

        return Jwts.builder()
                .subject(subject)
                .claim("id", jti)
                .claim("ait", now.getTime())
                .expiration(exp)
                .signWith(key_create, SignatureAlgorithm.HS512)
                .compact();
    }
}
