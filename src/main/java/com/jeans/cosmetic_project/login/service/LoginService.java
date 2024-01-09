package com.jeans.cosmetic_project.login.service;

import com.jeans.cosmetic_project.login.dto.LoginRequestDto;
import com.jeans.cosmetic_project.user.dto.User;

public interface LoginService {

    User verifyUser(LoginRequestDto loginRequestDto);
}
