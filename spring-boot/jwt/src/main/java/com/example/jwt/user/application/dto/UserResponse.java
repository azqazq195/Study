package com.example.jwt.user.application.dto;

import com.example.jwt.user.domain.Role;
import com.example.jwt.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
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
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getAge(),
                user.getRole(),
                user.getCreatedAt(),
                user.getModifiedAt(),
                user.getDeletedAt()
        );
    }
}
