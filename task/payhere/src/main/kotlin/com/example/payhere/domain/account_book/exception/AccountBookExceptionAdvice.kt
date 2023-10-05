package com.example.payhere.domain.account_book.exception

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
class AccountBookExceptionAdvice {
    private val log = logger()

    @ExceptionHandler(AccountBookNotFoundException::class)
    fun notFoundException(
        request: HttpServletRequest,
        e: AccountBookNotFoundException
    ): ResponseEntity<EmptyResult> {
        log.info("account book not found exception {}", e.message)
        return ResponseDto.of(ResponseCode.ACCOUNT_BOOK_NOT_FOUND)
    }

    @ExceptionHandler(AccountBookNoPermissionException::class)
    fun noPermissionException(
        request: HttpServletRequest,
        e: AccountBookNoPermissionException
    ): ResponseEntity<EmptyResult> {
        log.info("account book no permission exception {}", e.message)
        return ResponseDto.of(ResponseCode.ACCOUNT_BOOK_NO_PERMISSION)
    }
}