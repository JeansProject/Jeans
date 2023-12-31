package com.jeans.cosmetic_project.register.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegisterController {

    @GetMapping("/register")
    public String sign() {
        return "register/register";
    }
}
