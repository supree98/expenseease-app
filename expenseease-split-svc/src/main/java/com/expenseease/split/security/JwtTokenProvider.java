package com.expenseease.split.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String jwtSecret;

    public JwtTokenProvider(@Value("${jwt.secret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
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
