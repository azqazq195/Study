package com.example.jwt._common.exception

open class ApiException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)