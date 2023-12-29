package com.jeans.cosmetic_project.login.dao;

import com.jeans.cosmetic_project.login.dto.LoginRequestDto;
import com.jeans.cosmetic_project.user.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginServiceDao {

    User login(LoginRequestDto loginRequestDto);
}
