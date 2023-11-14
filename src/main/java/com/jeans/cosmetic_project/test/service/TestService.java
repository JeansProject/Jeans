package com.jeans.cosmetic_project.test.service;

import com.jeans.cosmetic_project.test.dto.LoginUser;
import com.jeans.cosmetic_project.test.dto.TestDto;
import com.jeans.cosmetic_project.test.dto.UserInfoDto;

public interface TestService {

    LoginUser login(TestDto loginUser);

    int register(TestDto loginUser);

    UserInfoDto getUserInfoById(String id);
}
