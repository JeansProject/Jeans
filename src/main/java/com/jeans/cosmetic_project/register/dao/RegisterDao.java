package com.jeans.cosmetic_project.register.dao;

import com.jeans.cosmetic_project.register.dto.RegisterRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterDao {
    void register(RegisterRequestDto registerRequestDto);
}
