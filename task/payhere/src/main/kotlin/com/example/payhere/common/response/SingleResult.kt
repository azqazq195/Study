package com.example.payhere.common.response

data class SingleResult<T>(
    override val success: Boolean,
    override val code: Int,
    override val msg: String,
    val data: T,
) : CommonResult