package com.example.springbootkotlinexample.domain.product.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.IReadDto
import com.example.springbootkotlinexample.domain.brand.controller.dto.ReadBrandDto
import java.time.LocalDateTime

data class ReadProductDto(
    val id: Long?,
    val name: String,
    val price: Int,
    val brand: ReadBrandDto,
    val createdAt: LocalDateTime?,
    val modifiedAt: LocalDateTime?
) : IReadDto
