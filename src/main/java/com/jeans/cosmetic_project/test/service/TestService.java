package com.jeans.cosmetic_project.test.service;

import com.jeans.cosmetic_project.test.dto.LoginUser;
import com.jeans.cosmetic_project.test.dto.TestDto;

import java.util.List;

public interface TestService {



    int register(TestDto loginUser);

    public LoginUser login(TestDto loginUser);
}
