package com.example.payhere.domain.auth.exception

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
class AuthExceptionAdvice {
    private val log = logger()

    @ExceptionHandler(SignInFailedException::class)
    fun signInFailedException(
        request: HttpServletRequest,
        e: SignInFailedException
    ): ResponseEntity<EmptyResult> {
        log.info("sign in failed exception {}", e.message)
        return ResponseDto.of(ResponseCode.SIGN_IN_FAILED)
    }

}