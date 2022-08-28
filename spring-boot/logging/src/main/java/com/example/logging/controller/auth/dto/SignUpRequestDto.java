package com.example.logging.controller.auth.dto;

import com.example.logging.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;

    public User toUser() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
