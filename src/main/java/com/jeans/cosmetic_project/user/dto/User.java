package com.jeans.cosmetic_project.user.dto;

import com.jeans.cosmetic_project.user.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
public class User {

    private UserRole role;
    private int seq;
    private String id;
    private String password;
    private String encodedPassword;
    private String name;
    private int age;
    private String email;
    private String phone;
    private String birthday;

    @Builder
    public User(UserRole role, int seq, String id, String name, int age, String email, String phone,
        String birthday) {
        this.role = role;
        this.seq = seq;
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
    }
}
