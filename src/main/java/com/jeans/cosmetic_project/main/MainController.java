package com.jeans.cosmetic_project.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String defaultRequest() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }
}
