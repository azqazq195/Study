package com.example.springbootkotlinexample.common.base.controller.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull

class ValidDtoList<T>(
    @field:Valid
    @field:NotNull
    val data: List<T>
)
