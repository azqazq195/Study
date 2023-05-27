package com.example.springbootkotlinexample.domain.user.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.IReadDto
import com.example.springbootkotlinexample.domain.user.entity.constants.GenderEnum
import java.util.*

data class ReadUserDto(
    val username: String,
    val password: String,
    val name: String,
    val nickName: String,
    val phone: String,
    val address: String?,
    val addressDetail: String?,
    val gender: GenderEnum?,
    val birthDay: Date?,
    val isCheckedPhone: Boolean,
    val isCheckedEmail: Boolean,
    val isReceivedPhone: Boolean,
    val isReceivedEmail: Boolean,
    val loginFailCount: Int,
    val withdrawReason: String?,
    val isBlocked: Boolean,
    val lastLoginAt: Date?,
    val adminRemark: String?
) : IReadDto
