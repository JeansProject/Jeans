package com.jeans.cosmetic_project.login.dao;

import com.jeans.cosmetic_project.login.dto.LoginRequestDto;
import com.jeans.cosmetic_project.user.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginDao {

    User verifyUser(LoginRequestDto loginRequestDto);

    List<User> findByUserName(String userName);
}
