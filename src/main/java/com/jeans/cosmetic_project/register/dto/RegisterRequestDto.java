package com.jeans.cosmetic_project.register.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {

    private String id;
    private String password;
    private String name;
    private int age;
    private String email;
    private String phone;
    private String birthday;
}
