package com.jeans.cosmetic_project.login.controller;

import com.jeans.cosmetic_project.common.sercurity.UsernamePwdAuthenticationProvider;
import com.jeans.cosmetic_project.login.dao.LoginDao;
import com.jeans.cosmetic_project.login.dto.LoginRequestDto;
import com.jeans.cosmetic_project.login.service.LoginService;
import com.jeans.cosmetic_project.login.service.LoginServiceImpl;
import com.jeans.cosmetic_project.user.dto.AuthenticatedUser;
import com.jeans.cosmetic_project.user.dto.Authority;
import com.jeans.cosmetic_project.user.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/login")
public class LoginController {

//    private final LoginService loginServiceImpl;
    private final LoginServiceImpl loginService;
    private final LoginDao loginDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsernamePwdAuthenticationProvider provider;
//
    @GetMapping
    public String login() {
        return "login/login";
    }

    @PostMapping("/verify")
    @ResponseBody
    public ResponseEntity verifyUser(@RequestBody LoginRequestDto loginRequestDto, Model model, HttpSession session) {
        String username = loginRequestDto.getId();
        String password = loginRequestDto.getPassword();

        List<UserDto> users = loginDao.findUserByUsername(username);
        if (users.size() > 0) {
            if(bCryptPasswordEncoder.matches(password, users.get(0).getPassword())) {
                UserDetails authenticatedUser = loginService.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(authenticatedUser, authenticatedUser.getPassword(), authenticatedUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return ResponseEntity.ok(authentication);
            }else {
                throw new BadCredentialsException("Invalid password");
            }
        }else {
            throw new BadCredentialsException("No user registered with this details!");
        }
    }

//    private List<GrantedAuthority> getGrantedAuthorities(int userSeq) {
//        List<Authority> authorities = loginDao.findAuthoritiesByUserId(userSeq);
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Authority authority : authorities) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
//        }
//        return grantedAuthorities;
//    }
}
