package com.example.springbootkotlinexample.domain.user.service

import com.example.springbootkotlinexample.common.base.service.AbstractService
import com.example.springbootkotlinexample.common.base.service.exception.NotFoundEntityException
import com.example.springbootkotlinexample.domain.user.controller.dto.CreateUserDto
import com.example.springbootkotlinexample.domain.user.controller.dto.ReadUserDto
import com.example.springbootkotlinexample.domain.user.controller.dto.UpdateUserDto
import com.example.springbootkotlinexample.domain.user.entity.User
import com.example.springbootkotlinexample.domain.user.entity.mapper.UserMapper
import com.example.springbootkotlinexample.domain.user.entity.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userMapper: UserMapper,
    private val userRepository: UserRepository
) : UserDetailsService, AbstractService<User, CreateUserDto, UpdateUserDto, ReadUserDto>(
    userMapper,
    userRepository
) {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username).orElseThrow { NotFoundEntityException(entityName()) }
    }
}
