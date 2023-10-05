package com.example.payhere.domain.auth.controller

import com.example.payhere.common.response.EmptyResult
import com.example.payhere.common.response.ResponseCode
import com.example.payhere.common.response.ResponseDto
import com.example.payhere.common.response.SingleResult
import com.example.payhere.domain.auth.controller.dto.SignInRequest
import com.example.payhere.domain.auth.controller.dto.SignInResponse
import com.example.payhere.domain.auth.controller.dto.SignUpRequest
import com.example.payhere.domain.auth.service.AuthService
import com.example.payhere.domain.auth.support.CurrentUser
import com.example.payhere.domain.user.controller.dto.UserDto
import com.example.payhere.domain.user.entity.User
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/sign-in")
    fun signIn(
        @RequestBody @Valid signInRequest: SignInRequest
    ): ResponseEntity<SingleResult<SignInResponse>> {
        val dto = authService.signIn(signInRequest)
        return ResponseDto.of(ResponseCode.SUCCESS, dto)
    }

    @GetMapping("/sign-out")
    fun signOut(auth: Authentication): ResponseEntity<EmptyResult> {
        authService.signOut(auth.credentials.toString())
        return ResponseDto.of(ResponseCode.SUCCESS)
    }

    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody @Valid signUpRequest: SignUpRequest
    ): ResponseEntity<EmptyResult> {
        authService.signUp(signUpRequest)
        return ResponseDto.of(ResponseCode.SUCCESS)
    }

    @GetMapping("/me")
    fun me(
        @CurrentUser user: User
    ): ResponseEntity<SingleResult<UserDto>> {
        val dto = authService.me(user)
        return ResponseDto.of(ResponseCode.SUCCESS, dto)
    }
}