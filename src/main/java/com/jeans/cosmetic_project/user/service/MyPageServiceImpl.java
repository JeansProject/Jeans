package com.jeans.cosmetic_project.user.service;

import com.jeans.cosmetic_project.user.dao.MyPageDao;
import com.jeans.cosmetic_project.user.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyPageServiceImpl implements MyPageService{

    private final MyPageDao myPageDao;

    @Override
    public User findUserById(String id) {
        return myPageDao.findUserById(id);
    }
}
