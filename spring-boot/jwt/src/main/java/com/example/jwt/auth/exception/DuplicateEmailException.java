package com.example.jwt.auth.exception;

import com.example.jwt._common.exception.ApiException;

public class DuplicateEmailException extends ApiException {

    private static final String message = "이미 존재하는 이메일입니다.";

    public DuplicateEmailException() {
        super(message);
    }
}
