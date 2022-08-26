package com.example.filterinterceptoraop.contoller.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
}
