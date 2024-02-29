package com.jeans.cosmetic_project.user.controller;

import com.jeans.cosmetic_project.user.dto.AuthenticatedUser;
import com.jeans.cosmetic_project.user.dto.UserDto;
import com.jeans.cosmetic_project.user.service.MyPageService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class MyPageController {

    private final MyPageService myPageServiceImpl;

    @GetMapping("/{id}")
    public String myPage(Model model, @PathVariable("id") String id) {
        UserDto userDto = myPageServiceImpl.findUserById(id);
        model.addAttribute("loginUser", userDto);
        return "myPage/myPage";
    }
}
