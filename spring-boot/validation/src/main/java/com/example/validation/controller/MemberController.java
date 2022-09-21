package com.example.validation.controller;

import com.example.validation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @PostMapping
    public ResponseEntity<String> createMember() {
        return ResponseEntity.ok().body("ok");
    }
}

