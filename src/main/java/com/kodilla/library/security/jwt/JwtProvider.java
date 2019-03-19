package com.kodilla.library.security.jwt;

import com.kodilla.library.security.AccountDetails;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.kodilla.library.security.SecurityConstants.JWT_EXPIRATION;
import static com.kodilla.library.security.SecurityConstants.JWT_SECRET;

@Component
@Slf4j
public class JwtProvider {

    public String generateJwtToken(Authentication authentication) {

        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(accountDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;

            //throw error -> one exception handler
        } catch (SignatureException e) {
            log.error("Invalid JWT signature. ", e);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token. ", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token. ", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token. ", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT string is empty. ", e);
        }

        return false;
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

}
