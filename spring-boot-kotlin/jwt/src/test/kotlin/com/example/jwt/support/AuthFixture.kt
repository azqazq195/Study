package com.example.jwt.support

import com.example.jwt.auth.application.dto.RefreshTokenRequest
import com.example.jwt.auth.application.dto.SignInRequest
import com.example.jwt.auth.application.dto.SignUpRequest
import com.example.jwt.auth.application.dto.TokenResponse
import com.example.jwt.auth.domain.Token
import java.util.*

fun createToken(): Token {
    return Token(
        email = "user@example.com",
        accessToken = "accessToken",
        refreshToken = "refreshToken",
        expiredAt = Date()
    )
}

fun createTokenResponse(): TokenResponse {
    return TokenResponse(
        accessToken = "accessToken",
        refreshToken = "refreshToken"
    )
}

fun createRefreshTokenRequest(): RefreshTokenRequest {
    return RefreshTokenRequest(
        refreshToken = "refreshToken",
        accessToken = "accessToken"
    )
}

fun createSignInRequest(): SignInRequest {
    return SignInRequest(
        email = "user@example.com",
        password = "password"
    )
}

fun createSignUpRequest(): SignUpRequest {
    return SignUpRequest(
        name = "name",
        password = "password",
        email = "user@example.com",
        age = 30
    )
}