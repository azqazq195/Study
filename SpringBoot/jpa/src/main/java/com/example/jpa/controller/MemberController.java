package com.example.jpa.controller;

import java.util.Optional;

import com.example.jpa.model.Member;
import com.example.jpa.model.Profile;
import com.example.jpa.model.Tag;
import com.example.jpa.repository.MemberRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@RequestMapping("/demo")
public class MemberController {
    // 단방향 OneToMany
    // private MemberRepository memberRepository;

    // @PostMapping(value = "")
    // public Member insert(@RequestBody Member member) {
    // return memberRepository.save(member);
    // }

    // @GetMapping(value = "")
    // public Optional<Member> get(@RequestParam("id") Long id) {
    // return memberRepository.findById(id);
    // }

    // 양방향 OneToMany
    private MemberRepository memberRepository;

    @PostMapping(value = "")
    public Member insert(@RequestBody Member newMember) {
        Member member = new Member();
        member.setName(newMember.getName());

        Profile profile = new Profile();
        profile.setIntroduce(newMember.getProfiles().get(0).getIntroduce());
        member.addProfile(profile);

        profile = new Profile();
        profile.setIntroduce(newMember.getProfiles().get(1).getIntroduce());
        member.addProfile(profile);

        member.removeProfile(profile);

        Tag tag = new Tag();
        tag.setName("spring");
        member.addTag(tag);

        tag = new Tag();
        tag.setName("AWS");
        member.addTag(tag);

        return memberRepository.save(member);
    }

    // @PostMapping(value = "/2")
    // public Member insert2(@RequestBody("name") String name,
    // @RequestBody("introduce") String introduce) {
    // return memberRepository.save(member);
    // }

    @GetMapping(value = "")
    public Optional<Member> get(@RequestParam("id") Long id) {
        return memberRepository.findById(id);
    }

    @DeleteMapping(value = "")
    public void delete(@RequestParam("id") Long id) {
        memberRepository.deleteById(id);
    }

    // @GetMapping(value = "")
    // public Optional<Member> get(@RequestParam("id") Long id) {
    // return memberRepository.findById(id);
    // }

}