package com.example.springbootkotlinexample.domain.user.entity.repository

import com.example.springbootkotlinexample.common.base.entity.repository.IRepository
import com.example.springbootkotlinexample.domain.user.entity.User
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : IRepository<User> {
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): Optional<User>
}
