package com.example.filterinterceptoraop.service;

import com.example.filterinterceptoraop.contoller.auth.dto.SignUpRequestDto;
import com.example.filterinterceptoraop.contoller.auth.dto.SignUpResponseDto;
import com.example.filterinterceptoraop.entity.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) {
        return signUpResponseDto("뭔가 전달");
    }
    
    public SignUpResponseDto signUpResponseDto(String param) {
        return new SignUpResponseDto("회원가입 완료");
    }

    public User me(String name, int age) {
        return User.builder()
                .id(age)
                .name(name)
                .email("azqazq195@gmail.com")
                .password("qwe")
                .build();
    }

    public Object error() {
        throw new RuntimeException("RuntimeException");
    }
}
