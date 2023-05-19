package com.example.jwt.auth.ui;

import com.example.jwt._common.application.dto.ApiResponse;
import com.example.jwt.auth.application.AuthService;
import com.example.jwt.auth.application.dto.RefreshTokenRequest;
import com.example.jwt.auth.application.dto.SignInRequest;
import com.example.jwt.auth.application.dto.SignUpRequest;
import com.example.jwt.auth.application.dto.TokenResponse;
import com.example.jwt.user.application.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<TokenResponse>> signIn(
            @RequestBody @Valid SignInRequest signInRequest
    ) {
        TokenResponse tokenResponse = authService.signIn(signInRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(tokenResponse));
    }

    @GetMapping("/sign-out")
    public ResponseEntity<Void> signOut(
            Authentication auth
    ) {
        authService.signOut(auth);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(
            @RequestBody @Valid SignUpRequest signUpRequest
    ) {
        authService.signUp(signUpRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<TokenResponse>> refresh(
            @RequestBody @Valid RefreshTokenRequest refreshTokenRequest
    ) {
        TokenResponse tokenResponse = authService.refresh(refreshTokenRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(tokenResponse));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> me(
            Authentication auth
    ) {
        UserResponse user = authService.me(auth);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(user));
    }
}
