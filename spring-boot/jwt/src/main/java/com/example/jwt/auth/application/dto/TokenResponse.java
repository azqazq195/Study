package com.example.jwt.auth.application.dto;

import com.example.jwt.auth.domain.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;

    public static TokenResponse of(Token token) {
        return new TokenResponse(
                token.getAccessToken(),
                token.getRefreshToken()
        );
    }
}
