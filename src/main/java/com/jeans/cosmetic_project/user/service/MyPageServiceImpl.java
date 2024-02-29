package com.jeans.cosmetic_project.user.service;

import com.jeans.cosmetic_project.user.dao.MyPageDao;
import com.jeans.cosmetic_project.user.dto.UserDto;
import com.jeans.cosmetic_project.user.exception.UpdateUserInformationFailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyPageServiceImpl implements MyPageService{

    private final MyPageDao myPageDao;

    @Override
    public UserDto findUserById(String id) {
        return myPageDao.findUserById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserDto user) {
        try{
            myPageDao.updateUser(user);
        }catch(Exception e) {
            throw new UpdateUserInformationFailException("Update to user information failed");
        }
    }
}
