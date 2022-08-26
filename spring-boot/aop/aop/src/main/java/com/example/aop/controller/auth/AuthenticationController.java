package com.example.aop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.aop.controller.dto.SignUpRequestDto;
import com.example.aop.service.AuthenticationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.ok().body(
                authenticationService.signup(signUpRequestDto)
        );
    }

    @GetMapping
    public ResponseEntity<String> greeting() {
        return ResponseEntity.ok().body(
                "hello"
        );
    }
}
