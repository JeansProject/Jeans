package com.jeans.cosmetic_project.test.service;

import com.jeans.cosmetic_project.test.dto.LoginUser;
import com.jeans.cosmetic_project.test.dto.TestDto;

public interface TestService {

    LoginUser login(TestDto loginUser);

    int register(TestDto loginUser);
}
