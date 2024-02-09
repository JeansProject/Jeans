package com.jeans.cosmetic_project.notice.service;

import com.jeans.cosmetic_project.notice.dto.LoginUser;
import com.jeans.cosmetic_project.notice.dto.TestDto;

public interface TestService {



    int register(TestDto loginUser);

    public LoginUser login(TestDto loginUser);
}
