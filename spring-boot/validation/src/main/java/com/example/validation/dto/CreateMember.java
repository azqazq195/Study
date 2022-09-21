package com.example.validation.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CreateMember {
    @Size(min = 2, max = 4)
    private String name;
    @Email
    private String email;
    private String password;
    private String phone;
    private String regNo;
}

