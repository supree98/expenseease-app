package com.expenseease.iam.security;

import com.expenseease.iam.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenProvider {

    private final String jwtSecret;
    private final int jwtExpirationInDays;

    public JwtTokenProvider(@Value("${jwt.secret}") String jwtSecret, @Value("${jwt.expiration}") int jwtExpirationInDays) {
        this.jwtSecret = jwtSecret;
        this.jwtExpirationInDays = jwtExpirationInDays;
    }

    public String generateToken(User user) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + TimeUnit.DAYS.toMillis(jwtExpirationInDays));

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            // Log signature exception
        } catch (MalformedJwtException ex) {
            // Log malformed JWT exception
        } catch (ExpiredJwtException ex) {
            // Log expired JWT exception
        } catch (UnsupportedJwtException ex) {
            // Log unsupported JWT exception
        } catch (IllegalArgumentException ex) {
            // Log illegal argument exception
        }
        return false;
    }
}
