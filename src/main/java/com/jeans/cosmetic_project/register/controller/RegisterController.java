package com.jeans.cosmetic_project.register.controller;

import com.jeans.cosmetic_project.common.annotation.LoginId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegisterController {

    @GetMapping("/register")
//    @TestAnnotation
    public String sign(@LoginId String test) {
        log.info("loginId = {}", test);
        return "register/register";
    }
}
