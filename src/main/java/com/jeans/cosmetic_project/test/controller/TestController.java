package com.jeans.cosmetic_project.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jeans.cosmetic_project.test.service.TestService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testServiceImpl;

    @GetMapping("/test")
    public String test() {
        return "test/test";
    }
    
    @GetMapping("/myPage")
    public String myPage() {
    	
    	return "test/myPage";
    	
    }

    @GetMapping("/login")
    public String login() {
    	
    	return "test/login";
    	
    }

//    @GetMapping("/sign")
//    public String sign() {
//
//    	return "test/sign";
//
//    }
   
}