package com.jeans.cosmetic_project.login.controller;

import com.jeans.cosmetic_project.login.dto.LoginRequestDto;
import com.jeans.cosmetic_project.login.service.LoginService;
import com.jeans.cosmetic_project.user.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginServiceImpl;

    @GetMapping
    public String login() {
        return "/login/login";
    }

    @PostMapping("/verify")
    public String verifyUser(@RequestBody LoginRequestDto loginRequestDto, Model model) {
        User loginUser = loginServiceImpl.login(loginRequestDto);
        model.addAttribute("loginUser", loginUser);
        return "/main";
    }
}
