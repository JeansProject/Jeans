package com.jeans.cosmetic_project.login.service;

import com.jeans.cosmetic_project.login.dao.LoginDao;
import com.jeans.cosmetic_project.login.dto.LoginRequestDto;
import com.jeans.cosmetic_project.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final LoginDao loginDao;

    public User verifyUser(LoginRequestDto loginRequestDto) {
        return loginDao.verifyUser(loginRequestDto);
    }
}
