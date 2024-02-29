package com.jeans.cosmetic_project.main.controller;

import com.jeans.cosmetic_project.common.annotation.LoginId;
import com.jeans.cosmetic_project.user.dto.AuthenticatedUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String defaultRequest() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal AuthenticatedUser user, Model model) {
        if(user != null) model.addAttribute("AUTH_USER", user);
        return "main";
    }
}
