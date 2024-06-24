package org.example.woowalearn.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.example.woowalearn.user.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    private final Key secretKey;
    private final long accessTokenExpiredTime;
    private final long refreshTokenExpiredTime;

    public JwtProvider(
            @Value("${security.jwt.token.secret-key}") final String secretKey,
            @Value("${security.jwt.access-token.expire-length}") final long accessTokenExpiredTime,
            @Value("${security.jwt.refresh-token.expire-length}") final long refreshTokenExpiredTime
            ) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.accessTokenExpiredTime = accessTokenExpiredTime;
        this.refreshTokenExpiredTime = refreshTokenExpiredTime;
    }

    public TokenResponse generateToken(final String subject) {
        return new TokenResponse(generateAccessToken(subject), generateRefreshToken(subject));
    }

    private String generateAccessToken(final String subject) {
        final Claims claims = Jwts.claims()
                .setSubject(subject);

        final Date now = new Date();
        final Date expiresIn = new Date(now.getTime() + accessTokenExpiredTime);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiresIn)
                .signWith(secretKey)
                .compact();
    }

    private String generateRefreshToken(final String subject) {
        final Claims claims = Jwts.claims()
                .setSubject(subject);

        final Date now = new Date();
        final Date expiresIn = new Date(now.getTime() + refreshTokenExpiredTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiresIn)
                .signWith(secretKey)
                .compact();
    }
}
