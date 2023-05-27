package com.example.springbootkotlinexample.domain.user.controller.dto

import com.example.springbootkotlinexample.domain.user.controller.validator.NotExistsUsername
import com.example.springbootkotlinexample.domain.user.entity.constants.GenderEnum
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CreateUserDto(
    @field:NotNull
    @field:Size(min = 2, max = 5)
    @field:NotExistsUsername
    val username: String?,

    @field:NotNull
    @field:Size(min = 2, max = 5)
    val password: String?,

    @field:NotNull
    @field:Size(min = 2, max = 5)
    val name: String?,

    @field:NotNull
    @field:Size(min = 2, max = 5)
    val nickName: String?,

    @field:NotNull
    @field:Size(min = 2, max = 5)
    val phone: String?,

    @field:Size(max = 100)
    val address: String?,

    @field:Size(max = 100)
    val addressDetail: String?,

    val gender: GenderEnum?
)
