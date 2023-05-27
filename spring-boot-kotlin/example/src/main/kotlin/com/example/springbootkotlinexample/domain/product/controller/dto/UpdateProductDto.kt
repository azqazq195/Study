package com.example.springbootkotlinexample.domain.product.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto
import com.example.springbootkotlinexample.common.base.controller.validator.ExistsEntity
import com.example.springbootkotlinexample.common.base.controller.validator.NotNullWhenBulk
import com.example.springbootkotlinexample.common.base.service.exception.RequiredArgumentException
import com.example.springbootkotlinexample.domain.brand.entity.Brand

class UpdateProductDto(
    @field:NotNullWhenBulk
    val id: Long? = null,
    val name: String?,
    val price: Int?,

    @field:ExistsEntity(entity = Brand::class)
    val brandId: Long?
) : IUpdateDto {
    override fun getId(): Long {
        if (id == null) throw RequiredArgumentException("id")
        return id
    }
}
