package com.jeans.cosmetic_project.user.controller;

import com.jeans.cosmetic_project.common.annotation.LoginId;
import com.jeans.cosmetic_project.user.dto.UserDto;
import com.jeans.cosmetic_project.user.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class MyPageRestController {

    private final MyPageService myPageServiceImpl;

    @PutMapping("/{seq}")
    public ResponseEntity updateUser(@RequestBody UserDto user, @PathVariable("seq") int seq) {
        user.setSeq(seq);
        myPageServiceImpl.updateUser(user);

        return ResponseEntity.ok(user);
    }
}
