package com.example.jwt.auth.exception;

import com.example.jwt._common.exception.ApiException;

public class NotFoundUserException extends ApiException {

    private static final String message = "사용자를 찾을 수 없습니다.";

    public NotFoundUserException() {
        super(message);
    }
}
