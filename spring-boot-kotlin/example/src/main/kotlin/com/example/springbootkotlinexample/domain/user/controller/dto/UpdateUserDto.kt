package com.example.springbootkotlinexample.domain.user.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto
import com.example.springbootkotlinexample.common.base.controller.validator.NotNullWhenBulk
import com.example.springbootkotlinexample.common.base.service.exception.RequiredArgumentException
import com.example.springbootkotlinexample.domain.user.entity.constants.GenderEnum
import java.util.Date

data class UpdateUserDto(
    @field:NotNullWhenBulk
    val id: Long?,
    val username: String?,
    val password: String?,
    val uuid: String?,
    val name: String?,
    val nickName: String?,
    val phone: String?,
    val address: String?,
    val addressDetail: String?,
    val gender: GenderEnum?,
    val birthDay: Date?,
    val isCheckedPhone: Boolean?,
    val isCheckedEmail: Boolean?,
    val isReceivedPhone: Boolean?,
    val isReceivedEmail: Boolean?,
    val withdrawReason: String?,
    val isBlocked: Boolean?,
    val adminRemark: String?
) : IUpdateDto {
    override fun getId(): Long {
        if (id == null) throw RequiredArgumentException("id")
        return id
    }
}
