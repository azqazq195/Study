package com.example.payhere.domain.user.entity

class UserPrincipal(
    val user: User
) : org.springframework.security.core.userdetails.User(
    user.email,
    user.password,
    emptyList()
)