package com.example.jwt.auth.exception;

import com.example.jwt._common.exception.ApiException;

public class NotMatchPasswordException extends ApiException {

    private static final String message = "비밀번호가 일치하지 않습니다.";

    public NotMatchPasswordException() {
        super(message);
    }
}