package com.example.converter.controller;

import com.example.converter.entity.Member;
import com.example.converter.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/init")
    public ResponseEntity<String> init() {
        memberRepository.save(Member.builder()
                .name("문성하")
                .regNo("993399-1234567")
                .deleted(true)
                .build());
        memberRepository.save(Member.builder()
                .name("문성하")
                .regNo("993399-1234567")
                .deleted(true)
                .build());
        memberRepository.save(Member.builder()
                .name("문성하")
                .regNo("993399-1234567")
                .deleted(false)
                .build());
        return ResponseEntity.ok().body("ok");
    }
}
