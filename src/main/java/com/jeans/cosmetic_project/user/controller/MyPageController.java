package com.jeans.cosmetic_project.user.controller;

import com.jeans.cosmetic_project.user.dto.User;
import com.jeans.cosmetic_project.user.service.MyPageService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class MyPageController {

    private final MyPageService myPageServiceImpl;

    @GetMapping("/{id}")
    public String myPage(Model model, @PathVariable("id") String id, HttpSession session) {

        log.info("loginUser = {}", session.getAttribute("loginUser"));
        User user = myPageServiceImpl.findUserById(id);
        model.addAttribute("loginUser", user);
        return "myPage/myPage";
    }

}
