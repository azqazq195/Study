package com.example.jwt._common.application.dto

class ListResult(
    statusCode: Int,
    message: String?,
    val data: List<Any>,
) : EmptyResult(statusCode, message)