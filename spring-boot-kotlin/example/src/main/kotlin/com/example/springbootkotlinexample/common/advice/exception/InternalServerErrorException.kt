package com.example.springbootkotlinexample.common.advice.exception

open class InternalServerErrorException(message: String? = null) : RuntimeException(message ?: "서버 오류가 발생했습니다.")
