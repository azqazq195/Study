package com.example.jwt.auth.application.dto;

import com.example.jwt.user.domain.Role;
import com.example.jwt.user.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequest {
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @Positive
    private int age;

    @NotBlank
    private String regNo;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .age(age)
                .regNo(regNo)
                .role(Role.USER)
                .build();
    }
}
