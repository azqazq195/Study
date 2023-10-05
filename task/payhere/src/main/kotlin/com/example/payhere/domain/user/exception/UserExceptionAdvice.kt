package com.example.payhere.domain.user.exception

import com.example.payhere.common.response.EmptyResult
import com.example.payhere.common.response.ResponseCode
import com.example.payhere.common.response.ResponseDto
import com.example.payhere.common.utils.logger
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class UserExceptionAdvice {
    private val log = logger()

    @ExceptionHandler(UserNotFoundException::class)
    fun notFoundException(
        request: HttpServletRequest,
        e: UserNotFoundException
    ): ResponseEntity<EmptyResult> {
        log.info("user not found exception {}", e.message)
        return ResponseDto.of(ResponseCode.USER_NOT_FOUND)
    }

    @ExceptionHandler(UserExpiredException::class)
    fun expiredException(
        request: HttpServletRequest,
        e: UserExpiredException
    ): ResponseEntity<EmptyResult> {
        log.info("user expired exception {}", e.message)
        return ResponseDto.of(ResponseCode.SIGN_IN_FAILED)
    }
}