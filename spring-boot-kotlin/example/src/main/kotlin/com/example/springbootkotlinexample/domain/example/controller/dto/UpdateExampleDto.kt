package com.example.springbootkotlinexample.domain.example.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto
import com.example.springbootkotlinexample.common.base.controller.validator.NotNullWhenBulk
import com.example.springbootkotlinexample.common.base.service.exception.RequiredArgumentException
import com.example.springbootkotlinexample.domain.example.entity.constants.ExampleEnum
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

/**
 * UpdateExampleDto
 *
 *
 * @property name 이름명: null 허용하지 않음, 최소 2자, 최대 5자
 * @property content 설명: null 허용하지 않음, 최소 2자, 최대 100자
 * @property count 개수: null 허용하지 않음, 최대 100
 * @property enum enum: null 허용하지 않음
 * @see ExampleEnum
 *
 */
data class UpdateExampleDto(
    @field:NotNullWhenBulk
    val id: Long?,

    @field:Size(min = 2, max = 5)
    val name: String?,

    @field:Size(min = 2, max = 100)
    val content: String?,

    @field:Max(100)
    val count: Int?,

    @field:NotNull
    val enum: ExampleEnum?
) : IUpdateDto {
    override fun getId(): Long {
        if (id == null) throw RequiredArgumentException("id")
        return id
    }
}
