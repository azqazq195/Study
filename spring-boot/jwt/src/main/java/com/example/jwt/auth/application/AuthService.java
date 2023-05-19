package com.example.jwt.auth.application;

import com.example.jwt.auth.application.dto.RefreshTokenRequest;
import com.example.jwt.auth.application.dto.SignInRequest;
import com.example.jwt.auth.application.dto.SignUpRequest;
import com.example.jwt.auth.application.dto.TokenResponse;
import com.example.jwt.auth.exception.DuplicateEmailException;
import com.example.jwt.auth.exception.NotFoundUserException;
import com.example.jwt.auth.exception.NotMatchPasswordException;
import com.example.jwt.user.application.dto.UserResponse;
import com.example.jwt.user.domain.User;
import com.example.jwt.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(NotFoundUserException::new);
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new NotMatchPasswordException();
        }
        return tokenProvider.create(user);
    }

    public void signOut(Authentication auth) {
        String accessToken = (String) auth.getCredentials();
        tokenProvider.deleteByAccessToken(accessToken);
    }

    @Transactional
    public void signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new DuplicateEmailException();
        }

        userRepository.save(signUpRequest.toUser(passwordEncoder));
    }

    public TokenResponse refresh(RefreshTokenRequest refreshTokenRequest) {
        return tokenProvider.refresh(refreshTokenRequest);
    }

    public UserResponse me(Authentication auth) {
        String email = (String) auth.getPrincipal();
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
        return UserResponse.of(user);
    }
}
