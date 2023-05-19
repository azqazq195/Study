package com.example.jwt.user.application.dto;

import com.example.jwt.user.domain.Role;
import com.example.jwt.user.domain.User;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class UserResponse {
    private long id;
    private String email;
    private String name;
    private int age;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .age(user.getAge())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .deletedAt(user.getDeletedAt())
                .build();
    }
}
