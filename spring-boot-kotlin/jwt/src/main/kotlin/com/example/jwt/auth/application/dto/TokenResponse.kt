package com.example.jwt.auth.application.dto

import com.example.jwt.auth.domain.Token

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
) {
    constructor(token: Token) : this(
        accessToken = token.accessToken,
        refreshToken = token.refreshToken
    )
}