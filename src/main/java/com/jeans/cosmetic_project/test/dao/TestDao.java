package com.jeans.cosmetic_project.test.dao;

import com.jeans.cosmetic_project.test.dto.LoginUser;
import com.jeans.cosmetic_project.test.dto.TestDto;
import com.jeans.cosmetic_project.test.dto.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDao {

    LoginUser login(TestDto loginUser);

    int register(TestDto registerUser);

    UserInfoDto getUserInfoById(String id);
}
