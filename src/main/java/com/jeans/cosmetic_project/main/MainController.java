package com.jeans.cosmetic_project.main;

import com.jeans.cosmetic_project.user.dto.AuthenticatedUser;
import com.jeans.cosmetic_project.user.dto.UserDto;
import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String defaultRequest() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal AuthenticatedUser user, Model model) {
        log.info("authentication = {}", user.toString());
        if(user != null) model.addAttribute("USER", user);
        return "main";
    }
}
