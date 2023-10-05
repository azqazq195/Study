package com.example.payhere.domain.user.controller.dto

import java.time.LocalDateTime

data class UserDto(
    val id: Long,
    val email: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime
)
