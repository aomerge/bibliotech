package com.aomerge.userservices.config.JWT;

import com.aomerge.userservices.config.access.AccesBinary;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class JWToken {
    private static final String KEY_CREATE = System.getenv("JWT_CREATE_KEY");
    private static final Key key_create = Keys.hmacShaKeyFor(KEY_CREATE.getBytes());
    private List<String> Logger;

    public static String CreateTokenUserTest(String subject ) {
        AccesBinary acces = new AccesBinary();
        acces.setPermition(acces.READ);
        acces.setPermition(acces.INFO);
        acces.setPermition(acces.RETURN);
        acces.setPermition(acces.BORROW);

        return GenerateToken(subject, 60, acces.getPermition(), "Madero");
    }

    public static String CreateTokenUser(String subject, String branchOffice, byte acces) {
        return GenerateToken(subject, 60*60, acces, branchOffice);
    }

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

    private static String GenerateToken(String subject, int expirationTime, byte acces, String branchOffice ) {
        String jti = UUID.randomUUID().toString();
        Date now = new Date();
        Date exp = new Date(now.getTime() + 60000L * expirationTime);

        return Jwts.builder()
                .subject(subject)
                .claim("Acces", acces )
                .claim("BranchOffice", branchOffice)
                .claim("id", jti)
                .claim("ait", now.getTime())
                .expiration(exp)
                .signWith(key_create)
                .compact();
    }
}
