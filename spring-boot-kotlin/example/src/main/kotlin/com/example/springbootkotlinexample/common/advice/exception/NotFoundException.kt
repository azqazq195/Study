package com.example.springbootkotlinexample.common.advice.exception

open class NotFoundException(message: String? = null) : BaseException(message ?: "존재하지 않는 데이터 입니다.")
