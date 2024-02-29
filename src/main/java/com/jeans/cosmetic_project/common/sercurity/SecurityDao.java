package com.jeans.cosmetic_project.common.sercurity;

import com.jeans.cosmetic_project.user.dto.Authority;
import com.jeans.cosmetic_project.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SecurityDao {
    List<UserDto> findUserByUsername(String username);

    List<Authority> findAuthoritiesByUserId(int userSeq);
}
