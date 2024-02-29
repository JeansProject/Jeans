package com.jeans.cosmetic_project.user.service;

import com.jeans.cosmetic_project.user.dto.UserDto;

public interface MyPageService {

    UserDto findUserById(String id);

    void updateUser(UserDto user);
}
