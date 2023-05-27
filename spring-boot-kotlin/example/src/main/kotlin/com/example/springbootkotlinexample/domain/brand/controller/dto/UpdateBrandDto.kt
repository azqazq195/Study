package com.example.springbootkotlinexample.domain.brand.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto
import com.example.springbootkotlinexample.common.base.controller.validator.NotNullWhenBulk
import com.example.springbootkotlinexample.common.base.service.exception.RequiredArgumentException

data class UpdateBrandDto(
    @field:NotNullWhenBulk
    val id: Long? = null,
    val name: String?
) : IUpdateDto {
    override fun getId(): Long {
        if (id == null) throw RequiredArgumentException("id")
        return id
    }
}
