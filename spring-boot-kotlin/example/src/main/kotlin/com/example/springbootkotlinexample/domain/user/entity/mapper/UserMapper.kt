package com.example.springbootkotlinexample.domain.user.entity.mapper

import com.example.springbootkotlinexample.common.base.entity.mapper.IMapper
import com.example.springbootkotlinexample.domain.user.controller.dto.CreateUserDto
import com.example.springbootkotlinexample.domain.user.controller.dto.ReadUserDto
import com.example.springbootkotlinexample.domain.user.controller.dto.UpdateUserDto
import com.example.springbootkotlinexample.domain.user.entity.User
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserMapper : IMapper<User, CreateUserDto, UpdateUserDto, ReadUserDto> {
    override fun toDto(entity: User): ReadUserDto {
        return ReadUserDto(
            username = entity.username,
            password = entity.password,
            name = entity.name,
            nickName = entity.nickName,
            phone = entity.phone,
            address = entity.address,
            addressDetail = entity.addressDetail,
            gender = entity.gender,
            birthDay = entity.birthDay,
            isCheckedPhone = entity.isCheckedPhone,
            isCheckedEmail = entity.isCheckedEmail,
            isReceivedPhone = entity.isReceivedPhone,
            isReceivedEmail = entity.isReceivedEmail,
            loginFailCount = entity.loginFailCount,
            withdrawReason = entity.withdrawReason,
            isBlocked = entity.isBlocked,
            lastLoginAt = entity.lastLoginAt,
            adminRemark = entity.adminRemark
        )
    }

    override fun toEntity(createDto: CreateUserDto): User {
        return User(
            username = createDto.username!!,
            password = createDto.password!!,
            uuid = UUID.randomUUID().toString(),
            salt = UUID.randomUUID().toString(),
            verifyToken = UUID.randomUUID().toString(),
            name = createDto.name!!,
            nickName = createDto.nickName!!,
            phone = createDto.phone!!,
            address = createDto.address,
            addressDetail = createDto.addressDetail,
            gender = createDto.gender
        )
    }

    override fun updateFromDto(updateDto: UpdateUserDto, entity: User): User {
        return entity.copy(
            username = updateDto.username ?: entity.username,
            password = updateDto.password ?: entity.password,
            name = updateDto.name ?: entity.name,
            nickName = updateDto.nickName ?: entity.nickName,
            phone = updateDto.phone ?: entity.phone,
            address = updateDto.address ?: entity.address,
            addressDetail = updateDto.addressDetail ?: entity.addressDetail,
            gender = updateDto.gender ?: entity.gender,
            birthDay = updateDto.birthDay ?: entity.birthDay,
            isCheckedPhone = updateDto.isCheckedPhone ?: entity.isCheckedPhone,
            isCheckedEmail = updateDto.isCheckedEmail ?: entity.isCheckedEmail,
            isReceivedPhone = updateDto.isReceivedPhone ?: entity.isReceivedPhone,
            isReceivedEmail = updateDto.isReceivedEmail ?: entity.isReceivedEmail,
            withdrawReason = updateDto.withdrawReason ?: entity.withdrawReason,
            isBlocked = updateDto.isBlocked ?: entity.isBlocked,
            adminRemark = updateDto.adminRemark ?: entity.adminRemark
        )
    }
}
