package com.jeans.cosmetic_project.register.service;

import com.jeans.cosmetic_project.register.dao.RegisterDao;
import com.jeans.cosmetic_project.register.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService{

    private final RegisterDao registerDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterRequestDto registerRequestDto) {
        try{
            registerDao.register(registerRequestDto);

        }catch(Exception e) {
            throw new IllegalArgumentException("회원가입에 필요한 정보를 다시 확인하세요.");
        }
    }
}
