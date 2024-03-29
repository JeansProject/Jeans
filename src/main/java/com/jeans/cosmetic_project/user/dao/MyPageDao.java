package com.jeans.cosmetic_project.user.dao;

import com.jeans.cosmetic_project.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageDao {

    UserDto findUserById(String id);

    void updateUser(UserDto user);
}
