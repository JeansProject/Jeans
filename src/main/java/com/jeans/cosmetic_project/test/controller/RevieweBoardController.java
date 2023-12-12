package com.jeans.cosmetic_project.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jeans.cosmetic_project.test.service.TestService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RevieweBoardController {

    private final TestService testServiceImpl;

     
    @GetMapping("/reviewBoard")
    public String reviewBoard() {
    	
    	return "test/reviewBoard";
    	
    }
   
}
