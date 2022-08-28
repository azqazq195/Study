package com.example.logging.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.logging.controller.auth.dto.SignUpResponseDto;
import com.example.logging.entity.User;

@Slf4j
@Service
public class AuthService {
    public SignUpResponseDto signup(User user) {
        logic(user);
        return new SignUpResponseDto("회원가입 완료");
    }

    public void logic(User user) {
        log.info("user {} 에 대한 로직 수행", user.getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
