package com.jeans.cosmetic_project.user.dao;

import com.jeans.cosmetic_project.user.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageDao {

    User findUserById(String id);
}
