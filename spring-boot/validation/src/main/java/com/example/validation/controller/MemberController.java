package com.example.validation.controller;

import com.example.validation.dto.CreateMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    @PostMapping
    public ResponseEntity<String> createMember(@RequestBody @Valid CreateMember createMember) {
        return ResponseEntity.ok().body("ok");
    }
}

