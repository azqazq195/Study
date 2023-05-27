package com.example.jwt.user.domain.repository

import com.example.jwt._common.domain.repository.DeletableRepository
import com.example.jwt.user.domain.User
import com.example.jwt.user.exception.NotFoundUserException
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : DeletableRepository<User> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): User?
}

fun UserRepository.getByEmail(email: String) = findByEmail(email)
    ?: throw NotFoundUserException()