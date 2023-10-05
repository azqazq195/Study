package com.example.payhere.common.exception

import com.example.payhere.common.response.EmptyResult
import com.example.payhere.common.response.ResponseCode
import com.example.payhere.common.response.ResponseDto
import com.example.payhere.common.utils.logger
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class ExceptionAdvice {
    private val log = logger()

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected fun defaultException(request: HttpServletRequest, e: Exception): ResponseEntity<EmptyResult> {
        log.info("unhandled exception {}", e.message)
        return ResponseDto.of(ResponseCode.FAIL)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<EmptyResult> {
        val firstFieldError = e.bindingResult.allErrors[0] as FieldError
        val message = "${firstFieldError.field}: ${firstFieldError.defaultMessage}"
        return ResponseDto.of(ResponseCode.BAD_REQUEST, message)
    }
}
