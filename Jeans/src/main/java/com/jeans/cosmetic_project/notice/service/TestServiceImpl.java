package com.jeans.cosmetic_project.notice.service;

import com.jeans.cosmetic_project.notice.dao.TestDao;
import com.jeans.cosmetic_project.notice.dto.LoginUser;
import com.jeans.cosmetic_project.notice.dto.TestDto;
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
}
