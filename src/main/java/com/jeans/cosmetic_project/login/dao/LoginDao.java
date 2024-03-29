package com.jeans.cosmetic_project.login.dao;

import com.jeans.cosmetic_project.user.dto.Authority;
import com.jeans.cosmetic_project.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import org.springframework.security.core.userdetails.User;

@Mapper
public interface LoginDao {

    List<UserDto> findUserByUsername(String userName);

    List<Authority> findAuthoritiesByUserId(int userSeq);

    User loadUserByUsername(String username);
}
