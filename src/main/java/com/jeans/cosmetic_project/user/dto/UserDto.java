package com.jeans.cosmetic_project.user.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int seq;
    private String id;
    private String password;
    private String name;
    private int age;
    private String email;
    private String phone;
    private String birthday;
    private String role;
    private Set<Authority> authorities;

}
