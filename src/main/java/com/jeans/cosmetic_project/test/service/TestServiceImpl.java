package com.jeans.cosmetic_project.test.service;

import com.jeans.cosmetic_project.test.dao.TestDao;
import com.jeans.cosmetic_project.test.dto.LoginUser;
import com.jeans.cosmetic_project.test.dto.TestDto;
import com.jeans.cosmetic_project.test.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestDao testDao;

    @Override
    public LoginUser login(TestDto loginUser) {
        LoginUser successLoginUser = testDao.login(loginUser);
        return successLoginUser;
    }

    @Override
    public int register(TestDto registerUser) {
        int registerResult = testDao.register(registerUser);
        return registerResult;
    }

    @Override
    public UserInfoDto getUserInfoById(String id) {
        UserInfoDto userInfoDto = testDao.getUserInfoById(id);
        return userInfoDto;
    }
}
