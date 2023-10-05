package com.example.payhere.common.response

import org.springframework.http.HttpStatus

enum class ResponseCode(
    val code: Int,
    val msg: String,
    val status: HttpStatus
) {
    SUCCESS(0, "요청 성공.", HttpStatus.OK),
    FAIL(-1, "요청 실패.", HttpStatus.INTERNAL_SERVER_ERROR),

    BAD_REQUEST(-10, "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),

    // AUTH
    UNAUTHORIZED(-1000, "로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED),
    SIGN_IN_FAILED(-1001, "이메일 또는 비밀번호가 정확하지 않습니다.", HttpStatus.UNAUTHORIZED),

    // USER
    USER_NOT_FOUND(-2000, "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    USER_EXPIRED(-2001, "사용자가 만료되었습니다.", HttpStatus.UNAUTHORIZED),

    // ACCOUNT_BOOK
    ACCOUNT_BOOK_NOT_FOUND(-3000, "가계부를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ACCOUNT_BOOK_NO_PERMISSION(-3001, "가계부에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN),
}
