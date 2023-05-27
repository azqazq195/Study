package com.example.springbootkotlinexample.domain.product.controller.dto

import com.example.springbootkotlinexample.common.base.controller.validator.ExistsEntity
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import jakarta.validation.constraints.NotNull

data class CreateProductDto(
    @field:NotNull
    val name: String?,

    @field:NotNull
    val price: Int?,

    @field:NotNull
    @field:ExistsEntity(entity = Brand::class)
    val brandId: Long?
)
