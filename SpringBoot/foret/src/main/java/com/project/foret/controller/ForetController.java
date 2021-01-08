package com.project.foret.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.project.foret.Model.Foret.*;
import com.project.foret.Repository.Foret.ForetRepository;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ForetController {

    private ForetRepository foretRepository;

    @GetMapping(value = "/foret/get")
    public Map<String, Object> get(@RequestParam("id") String id) {
        Map<String, Object> map = new HashMap<>();
        List<Foret> list = new ArrayList<>();
        list.add(foretRepository.findForetById(Integer.parseInt(id)));
        String result = list.get(0) != null ? "OK" : "FAIL";
        map.put("RT", result);
        if (result.equals("OK")) {
            map.put("member", list);
        }
        return map;
    }

    @PutMapping(value = "/foret/insert")
    public Foret insert(Foret foret, HttpServletRequest request, MultipartFile[] photos) throws Exception {
        String tag_name[] = request.getParameterValues("tag_name");
        String region_si[] = request.getParameterValues("region_si");
        String region_gu[] = request.getParameterValues("region_gu");
        if (tag_name != null) {
            for (int i = 0; i < tag_name.length; i++) {
                foret.getTag().add(new ForetTag(tag_name[i]));
            }
        }
        if (region_si != null) {
            for (int i = 0; i < region_si.length; i++) {
                foret.getRegion().add(new ForetRegion(region_si[i], region_gu[i]));
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

                ForetPhoto photo = new ForetPhoto();
                photo.setDir(dir);
                photo.setOriginname(originname);
                photo.setFilename(filename);
                photo.setFiletype(filetype);
                photo.setFilesize(filesize);

                foret.getPhoto().add(photo);
            }
        }
        return foretRepository.save(foret);
    }

    @DeleteMapping(value = "/foret/delete")
    public void delete(@RequestParam("id") String id) {
        foretRepository.deleteById(Integer.parseInt(id));
    }
}
