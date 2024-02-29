package com.jeans.cosmetic_project.common.sercurity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {

    private final SecurityDao securityDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) securityDao.findUserByUsername(username);
    }
}
