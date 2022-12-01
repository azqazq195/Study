package com.example.converter.member.controller

import com.example.converter.member.entity.Member
import com.example.converter.member.repository.MemberRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberRepository: MemberRepository
) {

    init {
        memberRepository.save(
            Member(
                id = 1,
                name = "문성하",
                regNo = "993399-1234567",
                deleted = true
            )
        )
        memberRepository.save(
            Member(
                id = 2,
                name = "문성하",
                regNo = "993399-1234567",
                deleted = false
            )
        )
        memberRepository.save(
            Member(
                id = 3,
                name = "문성하",
                regNo = "993399-1234567",
                deleted = false
            )
        )
    }

    @GetMapping
    fun findAll(): ResponseEntity<MutableList<Member>> {
        return ResponseEntity.ok().body(memberRepository.findAll())
    }
}