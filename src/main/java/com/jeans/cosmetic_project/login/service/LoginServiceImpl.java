package com.jeans.cosmetic_project.login.service;

import com.jeans.cosmetic_project.login.dao.LoginDao;
import com.jeans.cosmetic_project.login.dto.LoginRequestDto;
import com.jeans.cosmetic_project.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final LoginDao loginDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User verifyUser(LoginRequestDto loginRequestDto) {
        User user = loginDao.verifyUser(loginRequestDto);
        User verifiedUser;

        if(user != null && bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), user.getEncodedPassword())) {
            verifiedUser = User.builder()
                .seq(user.getSeq())
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .phone(user.getPhone())
                .birthday(user.getBirthday())
                .role(user.getRole())
                .build();
        }
        else verifiedUser = null;

        return verifiedUser;
    }
}
