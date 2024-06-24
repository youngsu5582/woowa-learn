package org.example.woowalearn.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.example.woowalearn.user.domain.UserPrincipal;
import org.example.woowalearn.user.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    private final Key secretKey;
    private final long accessTokenExpiredTime;
    private final long refreshTokenExpiredTime;
    private final UserPrincipalService userPrincipalService;

    public JwtProvider(
            @Value("${security.jwt.token.secret-key}") final String secretKey,
            @Value("${security.jwt.access-token.expire-length}") final long accessTokenExpiredTime,
            @Value("${security.jwt.refresh-token.expire-length}") final long refreshTokenExpiredTime,
            final UserPrincipalService userPrincipalService) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.accessTokenExpiredTime = accessTokenExpiredTime;
        this.refreshTokenExpiredTime = refreshTokenExpiredTime;
        this.userPrincipalService = userPrincipalService;
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

    public void validateToken(final String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (final IllegalArgumentException e) {
            throw new JwtException("타당하지 않은 토큰입니다",e);
        }
    }

    public Authentication getAuthentication(final String token) {
        final Claims claims = parseClaims(token);
        final UserPrincipal userPrincipal = userPrincipalService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
    }

    private Claims parseClaims(final String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (final ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}

