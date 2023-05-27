package com.example.springbootkotlinexample.common.base.service.exception

import com.example.springbootkotlinexample.common.advice.exception.BadRequestException

open class RequiredArgumentException(argName: String) : BadRequestException("$argName 값은 필수 값 입니다.")
