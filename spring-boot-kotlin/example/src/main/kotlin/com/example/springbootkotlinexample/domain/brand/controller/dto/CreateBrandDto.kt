package com.example.springbootkotlinexample.domain.brand.controller.dto

import jakarta.validation.constraints.NotNull

data class CreateBrandDto(
    @field:NotNull
    val name: String?
)
