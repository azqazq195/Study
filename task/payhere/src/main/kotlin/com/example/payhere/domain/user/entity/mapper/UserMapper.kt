package com.example.payhere.domain.user.entity.mapper

import com.example.payhere.domain.user.controller.dto.UserDto
import com.example.payhere.domain.user.entity.User
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun getDto(user: User): UserDto {
        return UserDto(
            id = user.id!!,
            email = user.email,
            createdAt = user.createdAt!!,
            modifiedAt = user.modifiedAt!!
        )
    }
}
