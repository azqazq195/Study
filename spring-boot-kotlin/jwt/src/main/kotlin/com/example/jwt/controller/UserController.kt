package com.example.jwt.controller

import com.example.jwt.entity.User
import com.example.jwt.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping()
    fun hello() = "hello"

    @GetMapping("/1")
    fun findAll() = userService.findAll()

    @GetMapping("/2")
    fun findAll2(): List<User> {
        return userService.findAll()
    }
}