package com.example.filterinterceptoraop.contoller.auth;

import com.example.filterinterceptoraop.contoller.auth.dto.SignUpRequestDto;
import com.example.filterinterceptoraop.contoller.auth.dto.SignUpResponseDto;
import com.example.filterinterceptoraop.entity.User;
import com.example.filterinterceptoraop.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.ok().body(
                authService.signup(signUpRequestDto)
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
