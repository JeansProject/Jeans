package com.jeans.cosmetic_project.test.controller;

import com.jeans.cosmetic_project.test.dto.LoginUser;
import com.jeans.cosmetic_project.test.dto.TestDto;
import com.jeans.cosmetic_project.test.service.TestService;
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
public class TestRestController {

    private final TestService testServiceImpl;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody TestDto loginUser) {
        log.info("loginUserId = {}", loginUser.getId());
        log.info("loginUserPassword = {}", loginUser.getPassword());

        LoginUser successLoginUser = testServiceImpl.login(loginUser);
        log.info("successLoginUserSeq = {}", successLoginUser.getSeq());
        log.info("successLoginUserId = {}", successLoginUser.getId());
        log.info("successLoginUserName = {}", successLoginUser.getName());

        return new ResponseEntity(successLoginUser, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody TestDto loginUser) {
        log.info("loginUserId = {}", loginUser.getId());
        log.info("loginUserPassword = {}", loginUser.getPassword());

        int registerResult = testServiceImpl.register(loginUser);
        log.info("result = {}", registerResult);

        return new ResponseEntity(registerResult, HttpStatus.OK);
    }

}
