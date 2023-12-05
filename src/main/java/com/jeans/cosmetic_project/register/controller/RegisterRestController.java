package com.jeans.cosmetic_project.register.controller;

import com.jeans.cosmetic_project.register.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterRestController {

    @PostMapping
    public ResponseEntity register(@RequestBody RegisterRequestDto registerRequestDto) {
        log.info("register user = {}", registerRequestDto.toString());
        return new ResponseEntity("response", HttpStatus.OK);
    }
}
