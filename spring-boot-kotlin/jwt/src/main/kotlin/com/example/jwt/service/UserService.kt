package com.example.jwt.service

import com.example.jwt.entity.User
import com.example.jwt.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username).orElseThrow { UsernameNotFoundException("사용자를 찾을 수 없음.") }
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }
}
