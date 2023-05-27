package com.example.springbootkotlinexample.domain.example.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.IReadDto
import com.example.springbootkotlinexample.domain.example.entity.constants.ExampleEnum
import java.time.LocalDateTime

data class ReadExampleDto(
    val name: String,
    val content: String,
    val count: Int,
    val enum: ExampleEnum,
    val createdAt: LocalDateTime?,
    val modifiedAt: LocalDateTime?
) : IReadDto
