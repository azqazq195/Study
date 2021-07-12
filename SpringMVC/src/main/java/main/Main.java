package main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * main.Main
 * 주소
 * Github : http://github.com/azqazq195
 * Created by azqazq195@gmail.com on 2021-07-12
 */

@Controller
public class Main {

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("text", "Spring MVC Page.<br/>Comming Soon.");
        return "index";
    }

    @RequestMapping(value = "/json", produces = "application/json")
    @ResponseBody
    public Object json() {
        Map<String, String> map = new HashMap<>();
        map.put("text", "MVC Spring. json test");
        return map;
    }
}
