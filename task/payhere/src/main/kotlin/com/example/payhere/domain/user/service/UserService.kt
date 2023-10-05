package com.example.payhere.domain.user.service

import com.example.payhere.domain.user.entity.UserPrincipal
import com.example.payhere.domain.user.entity.repository.UserRepository
import com.example.payhere.domain.user.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
) : UserDetailsService {

    @Throws(UserNotFoundException::class)
    override fun loadUserByUsername(userPk: String): UserPrincipal {
        val entity = repository.findById(userPk.toLong()).orElseThrow { UserNotFoundException() }
        return UserPrincipal(entity)
    }
}