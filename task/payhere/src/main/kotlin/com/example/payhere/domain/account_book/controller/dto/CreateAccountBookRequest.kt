package com.example.payhere.domain.account_book.controller.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateAccountBookRequest(
    @field:NotBlank
    val note: String?,

    @field:NotNull
    val amount: Int?,
)