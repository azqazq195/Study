package com.example.jwt.auth.domain.repository;

import com.example.jwt.auth.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    Optional<Token> findByRefreshToken(String refreshToken);

    void deleteByAccessToken(String accessToken);
}
