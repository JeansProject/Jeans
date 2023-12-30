package com.jeans.cosmetic_project.login.service;

import com.jeans.cosmetic_project.login.dao.LoginDao;
import com.jeans.cosmetic_project.login.dto.LoginRequestDto;
import com.jeans.cosmetic_project.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final LoginDao loginDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User verifyUser(LoginRequestDto loginRequestDto) {
        User loginUser = null;
        User user = loginDao.verifyUser(loginRequestDto);

        if(user != null && bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            loginUser = user;
        }

        return loginUser;
    }
}
