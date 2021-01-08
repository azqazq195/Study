package com.project.foret.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.project.foret.Model.Foret.ForetBoard;
import com.project.foret.Model.Foret.ForetBoardPhoto;
import com.project.foret.Repository.Foret.ForetBoardRepository;

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
public class BoardController {

    private ForetBoardRepository foretBoardRepository;

    @GetMapping(value = "/foretboard/get")
    public Map<String, Object> get(@RequestParam("id") String id) {
        Map<String, Object> map = new HashMap<>();
        List<ForetBoard> list = new ArrayList<>();
        list.add(foretBoardRepository.findBoardById(Integer.parseInt(id)));
        String result = list.get(0) != null ? "OK" : "FAIL";
        map.put("RT", result);
        if (result.equals("OK")) {
            map.put("member", list);
        }
        return map;
    }

    @PutMapping(value = "/foretboard/insert")
    public ForetBoard insert(ForetBoard foretBoard, HttpServletRequest request, MultipartFile[] photos)
            throws Exception {
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

                ForetBoardPhoto photo = new ForetBoardPhoto();
                photo.setDir(dir);
                photo.setOriginname(originname);
                photo.setFilename(filename);
                photo.setFiletype(filetype);
                photo.setFilesize(filesize);

                foretBoard.getPhoto().add(photo);
            }
        }
        return foretBoardRepository.save(foretBoard);
    }

    @DeleteMapping(value = "/foretboard/delete")
    public void delete(@RequestParam("id") String id) {
        foretBoardRepository.deleteById(Integer.parseInt(id));
    }
}
