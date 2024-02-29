package com.jeans.cosmetic_project.login.service;

import com.jeans.cosmetic_project.login.dao.LoginDao;
import com.jeans.cosmetic_project.login.dto.LoginRequestDto;
import com.jeans.cosmetic_project.user.dto.AuthenticatedUser;
import com.jeans.cosmetic_project.user.dto.Authority;
import com.jeans.cosmetic_project.user.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements UserDetailsService {

    private final LoginDao loginDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<UserDto> users = loginDao.findUserByUsername(username);
//        User user = new User(users.get(0).getId(), users.get(0).getPassword(), getGrantedAuthorities(users.get(0).getSeq()));
//        if(user == null) {
//            return null;
//        } else {
//            return new AuthenticatedUser(user);
//        }
        return null;
    }

    private List<GrantedAuthority> getGrantedAuthorities(int userSeq) {
        List<Authority> authorities = loginDao.findAuthoritiesByUserId(userSeq);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }
}
