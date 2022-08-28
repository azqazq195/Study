package com.example.logging.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.logging.controller.auth.dto.SignUpRequestDto;
import com.example.logging.controller.auth.dto.SignUpResponseDto;
import com.example.logging.service.AuthService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.ok().body(
                authService.signup(signUpRequestDto.toUser())
        );
    }

    @GetMapping("/me")
    public ResponseEntity<Object> me(@RequestParam String name, @RequestParam int age) {
        return ResponseEntity.ok().body(
                authService.me(name, age)
        );
    }

    @GetMapping("/error")
    public ResponseEntity<Object> error() {
        return ResponseEntity.ok().body(
                authService.error()
        );
    }
}
