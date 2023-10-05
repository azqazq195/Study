package com.example.payhere.domain.auth.service

import com.example.payhere.domain.auth.controller.dto.SignInRequest
import com.example.payhere.domain.auth.controller.dto.SignInResponse
import com.example.payhere.domain.auth.controller.dto.SignUpRequest
import com.example.payhere.domain.auth.exception.SignInFailedException
import com.example.payhere.domain.auth.support.JwtStorage
import com.example.payhere.domain.user.controller.dto.UserDto
import com.example.payhere.domain.user.entity.User
import com.example.payhere.domain.user.entity.mapper.UserMapper
import com.example.payhere.domain.user.entity.repository.UserRepository
import com.example.payhere.domain.user.exception.UserExpiredException
import com.example.payhere.domain.user.exception.UserNotFoundException
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val mapper: UserMapper,
    private val repository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun signIn(signInRequest: SignInRequest): SignInResponse {
        val user = repository
            .findByEmail(signInRequest.email!!)
            .orElseThrow { UserNotFoundException() }
            .apply { if (!passwordEncoder.matches(signInRequest.password!!, password)) throw SignInFailedException() }
            .apply { if (deletedAt != null) throw UserExpiredException() }

        val accessToken = jwtTokenProvider.createToken(user.id.toString())
        return SignInResponse(accessToken = accessToken)
    }

    fun signOut(token: String) {
        JwtStorage.signOut(token)
    }

    @Transactional
    fun signUp(signUpRequest: SignUpRequest) {
        repository.save(
            User(
                email = signUpRequest.email!!,
                password = passwordEncoder.encode(signUpRequest.password!!)
            )
        )
    }

    @Transactional
    fun me(currentUser: User): UserDto {
        return mapper.getDto(currentUser)
    }
}