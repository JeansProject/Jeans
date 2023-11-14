package com.jeans.cosmetic_project.test.controller;

import com.jeans.cosmetic_project.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testServiceImpl;

    @GetMapping("/test")
    public String test() {
        return "test/test";
    }

    @GetMapping("/my-page")
    public String myPage() {
        return "test/myPage";
    }
}