package com.example.payhere.domain.account_book.controller.dto

import java.time.LocalDateTime

data class AccountBookDto(
    val id: Long,
    val note: String,
    val amount: Int,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime
)