package com.example.jwt.auth.application;

import com.example.jwt.auth.application.dto.RefreshTokenRequest;
import com.example.jwt.auth.application.dto.TokenResponse;
import com.example.jwt.auth.domain.Token;
import com.example.jwt.auth.domain.repository.TokenRepository;
import com.example.jwt.auth.exception.NotFoundUserException;
import com.example.jwt.auth.exception.NotMatchAccessTokenException;
import com.example.jwt.user.domain.User;
import com.example.jwt.user.domain.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class TokenProvider {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    @Value("${config.jwt.secret}")
    private String accessSecret;
    @Value("${config.jwt.access-token-expire-time}")
    private Long accessTokenExpireTime;
    @Value("${config.jwt.refresh-secret}")
    private String refreshSecret;
    @Value("${config.jwt.refresh-token-expire-time}")
    private Long refreshTokenExpireTime;

    private static final String ROLE = "role";
    private final Map<String, Date> blackList = new HashMap<>();
    private Key accessKey;
    private Key refreshKey;

    @PostConstruct
    protected void init() {
        this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret));
        this.refreshKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecret));
    }

    @Transactional
    public TokenResponse create(User user) {
        Token token = Token.builder()
                .email(user.getEmail())
                .accessToken(createAccessToken(user))
                .refreshToken(createRefreshToken(user))
                .expiredAt(new Date(System.currentTimeMillis() + refreshTokenExpireTime))
                .build();

        return TokenResponse.of(tokenRepository.save(token));
    }

    @Transactional
    public void deleteByAccessToken(String accessToken) {
        setBlackList(accessToken);
        tokenRepository.deleteByAccessToken(accessToken);
    }

    @Transactional
    public TokenResponse refresh(RefreshTokenRequest refreshTokenRequest) {
        Token token = tokenRepository
                .findByRefreshToken(refreshTokenRequest.getRefreshToken())
                .orElseThrow(RuntimeException::new);

        if (!token.getAccessToken().equals(refreshTokenRequest.getAccessToken())) {
            throw new NotMatchAccessTokenException();
        }

        deleteByAccessToken(token.getAccessToken());
        User user = userRepository.findByEmail(token.getEmail()).orElseThrow(NotFoundUserException::new);
        return create(user);
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        String email = claims.getSubject();
        Collection<GrantedAuthority> authorities = new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority("ROLE_" + claims.get(ROLE).toString())));
        return new UsernamePasswordAuthenticationToken(email, token, authorities);
    }

    public boolean validateToken(String token) {
        if (blackList.containsKey(token)) return false;

        try {
            getClaims(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        } catch (Exception e) {
            log.error("JWT token is invalid: {}", e.getMessage());
        }

        return false;
    }

    private String createAccessToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + accessTokenExpireTime);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim(ROLE, user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private String createRefreshToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + refreshTokenExpireTime);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim(ROLE, user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(refreshKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims getClaims(String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(accessKey)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
    }

    private void setBlackList(String accessToken) {
        Claims claims = getClaims(accessToken);
        Date expiration = claims.getExpiration();
        blackList.put(accessToken, expiration);
    }
}
