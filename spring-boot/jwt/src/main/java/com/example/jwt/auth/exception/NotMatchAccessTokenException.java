package com.example.jwt.auth.exception;

import com.example.jwt._common.exception.ApiException;

public class NotMatchAccessTokenException extends ApiException {

    private static final String message = "토큰 정보가 올바르지 않습니다.";

    public NotMatchAccessTokenException() {
        super(message);
    }
}
