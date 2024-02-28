package com.jeans.cosmetic_project.login.service;

import com.jeans.cosmetic_project.login.dto.LoginRequestDto;
import com.jeans.cosmetic_project.user.dto.UserDto;

public interface LoginService {

    UserDto verifyUser(LoginRequestDto loginRequestDto);
}
