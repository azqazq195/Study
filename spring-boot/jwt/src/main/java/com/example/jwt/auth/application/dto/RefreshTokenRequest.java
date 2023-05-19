package com.example.jwt.auth.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RefreshTokenRequest {
    @NotBlank
    private String accessToken;

    @NotBlank
    private String refreshToken;
}
