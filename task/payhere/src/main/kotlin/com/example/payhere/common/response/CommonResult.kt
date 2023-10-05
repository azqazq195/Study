package com.example.payhere.common.response

interface CommonResult {
    val success: Boolean
    val code: Int
    val msg: String
}