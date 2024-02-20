package com.jeans.cosmetic_project.user.dto;

import com.jeans.cosmetic_project.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

//    private UserRole role;
    private int seq;
    private String id;
    private String password;
    private String encodedPassword;
    private String name;
    private int age;
    private String email;
    private String phone;
    private String birthday;
    private String role;

    @Builder
    public User(int seq, String id, String name, int age, String email, String phone,
        String birthday, String role) {
//        this.role = role;
        this.seq = seq;
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.role = role;
    }
}
