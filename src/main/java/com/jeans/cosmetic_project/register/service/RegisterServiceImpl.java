package com.jeans.cosmetic_project.register.service;

import com.jeans.cosmetic_project.register.dao.RegisterDao;
import com.jeans.cosmetic_project.register.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService{

    private final RegisterDao registerDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterRequestDto registerRequestDto) {
        String encodedPassword = bCryptPasswordEncoder.encode(registerRequestDto.getPassword());
        registerRequestDto.setEncodedPassword(encodedPassword);
        registerDao.register(registerRequestDto);
    }
}
