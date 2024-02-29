package com.jeans.cosmetic_project.common.sercurity;

import com.jeans.cosmetic_project.user.dto.Authority;
import com.jeans.cosmetic_project.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 인증을 진행하고 인증된 객체를 넘겨주는 작업을 진행
 */
@Component
@RequiredArgsConstructor
public class UsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final SecurityDao securityDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * UserDetailsService에서 담당하던 사용자 정보 조회에 대한 로직도 AuthenticationProvider에 포함
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<UserDto> users = securityDao.findUserByUsername(username);
        if (users.size() > 0) {
            if(bCryptPasswordEncoder.matches(password, users.get(0).getPassword())) {
//                List<GrantedAuthority> authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority(users.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthorities(users.get(0).getSeq()));
            }else {
                throw new BadCredentialsException("Invalid password");
            }
        }else {
            throw new BadCredentialsException("No user registered with this details!");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(int userSeq) {
        List<Authority> authorities = securityDao.findAuthoritiesByUserId(userSeq);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
