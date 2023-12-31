package com.jeans.cosmetic_project.user.dto;

import lombok.Data;

@Data
public class User {

    private String id;
    private String password;
    private String encodedPassword;
    private String name;
    private int age;
    private String email;
    private String phone;
    private String birthday;
}
