package com.project.foret.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.project.foret.Model.Foret.Foret;
import com.project.foret.Model.Member.*;
import com.project.foret.Repository.Member.MemberRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
public class MemberController {

    private MemberRepository memberRepository;

    @GetMapping(value = "/member/getAll")
    public Map<String, Object> getAll() {
        Map<String, Object> map = new HashMap<>();
        List<Member> list = memberRepository.findAll();
        String result = list != null ? "OK" : "FAIL";
        map.put("RT", result);
        if (result.equals("OK")) {
            map.put("member", list);
        }
        return map;
    }

    @GetMapping(value = "/member/get")
    public Map<String, Object> get(@RequestParam("id") String id) {
        Map<String, Object> map = new HashMap<>();
        List<Member> list = new ArrayList<>();
        list.add(memberRepository.findMemberById(Integer.parseInt(id)));
        String result = list.get(0) != null ? "OK" : "FAIL";
        map.put("RT", result);
        if (result.equals("OK")) {
            map.put("member", list);
        }
        return map;
    }

    @PutMapping(value = "/member/insert")
    public Member insert(Member member, HttpServletRequest request, MultipartFile[] photos) throws Exception {
        String tag_name[] = request.getParameterValues("tag_name");
        String region_si[] = request.getParameterValues("region_si");
        String region_gu[] = request.getParameterValues("region_gu");
        if (tag_name != null) {
            for (int i = 0; i < tag_name.length; i++) {
                member.getTag().add(new MemberTag(tag_name[i]));
            }
        }
        if (region_si != null) {
            for (int i = 0; i < region_si.length; i++) {
                member.getRegion().add(new MemberRegion(region_si[i], region_gu[i]));
            }
        }
        if (photos != null) {
            String dir = System.getProperty("user.dir") + "/src/main/resources/storage";
            System.out.println("위치");
            System.out.println(dir);
            for (int i = 0; i < photos.length; i++) {
                String originname = photos[i].getOriginalFilename();
                String filename = photos[i].getOriginalFilename();
                int lastIndex = originname.lastIndexOf(".");
                String filetype = originname.substring(lastIndex + 1);
                int filesize = (int) photos[i].getSize();
                File file = new File(dir, filename);
                FileCopyUtils.copy(photos[i].getInputStream(), new FileOutputStream(file));

                MemberPhoto photo = new MemberPhoto();
                photo.setDir(dir);
                photo.setOriginname(originname);
                photo.setFilename(filename);
                photo.setFiletype(filetype);
                photo.setFilesize(filesize);

                member.getPhoto().add(photo);
            }
        }
        return memberRepository.save(member);
    }

    @DeleteMapping(value = "/member/delete")
    public void delete(@RequestParam("id") String id) {
        memberRepository.deleteById(Integer.parseInt(id));
    }

    @DeleteMapping(value = "/member/deleteAll")
    public void deleteAll() {
        memberRepository.deleteAll();
    }

    @PostMapping(value = "/member/insertforetboard")
    public void insertForetBoard(@RequestParam("member_id") String member_id, Foret foret, HttpServletRequest request,
            MultipartFile[] photos) throws Exception {

    }

    @GetMapping(value = "/member/test")
    public void test(HttpServletRequest request) {
    }

}
