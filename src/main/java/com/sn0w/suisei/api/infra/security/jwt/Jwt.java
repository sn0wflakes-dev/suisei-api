package com.sn0w.suisei.api.infra.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class Jwt {

    private static Logger log = LogManager.getLogger(Jwt.class);

    @Value("${jwt.secrets}")
    private String secret;

    @Value("${jwt.expire}")
    private long expire;

    private SecretKey getSignKey() {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(secret);
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (Throwable t) {
            log.error(t);
            throw t;
        }
    }

    public String generateToken(UserDetails userDetails) {
        log.debug(">>> 1 secret={} expire={}", secret, expire);

        SecretKey key = getSignKey();
        log.debug(">>> 2 key ok: {}", key.getAlgorithm());

        Date now = new Date();
        log.debug(">>> 3 issuedAt={}", now);

        Date exp = new Date(System.currentTimeMillis() + expire);
        log.debug(">>> 4 expiration={}", exp);

        String token = Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .expiration(exp)
                .signWith(key)
                .compact();

        log.debug(">>> 5 token={}", token);
        return token;
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        String identifier = extractUsername(token);
        return identifier.equals(userDetails.getUsername()) && !isTokenExpire(token);
    }

    private Boolean isTokenExpire(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
