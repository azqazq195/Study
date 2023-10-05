package com.example.payhere.domain.user.entity.repository

import com.example.payhere.common.entity.repository.BaseTimeRepository
import com.example.payhere.domain.user.entity.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : BaseTimeRepository<User> {
    fun findByEmail(email: String): Optional<User>
}