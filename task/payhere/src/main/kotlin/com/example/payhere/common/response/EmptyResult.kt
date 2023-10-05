package com.example.payhere.common.response

data class EmptyResult(
    override val success: Boolean,
    override val code: Int,
    override val msg: String,
) : CommonResult