package com.example.payhere.domain.auth.controller.dto

import com.example.payhere.domain.auth.controller.validator.NotExistsEmail
import jakarta.validation.constraints.NotBlank

data class SignUpRequest(
    @field:NotBlank
    @field:NotExistsEmail
    val email: String?,

    @field:NotBlank
    val password: String?
)