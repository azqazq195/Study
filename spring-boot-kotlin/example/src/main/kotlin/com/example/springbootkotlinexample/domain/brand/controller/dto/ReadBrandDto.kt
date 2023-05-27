package com.example.springbootkotlinexample.domain.brand.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.IReadDto
import java.time.LocalDateTime

data class ReadBrandDto(
    val id: Long?,
    val name: String,
    val createdAt: LocalDateTime?,
    val modifiedAt: LocalDateTime?
) : IReadDto
