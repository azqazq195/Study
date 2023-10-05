package com.example.payhere.common.response

data class ListResult<T>(
    override val success: Boolean,
    override val code: Int,
    override val msg: String,
    val data: List<T>
) : CommonResult