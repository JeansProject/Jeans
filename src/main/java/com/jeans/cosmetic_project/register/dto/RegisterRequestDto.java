package com.jeans.cosmetic_project.register.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequestDto {

    private String id;
    private String password;
    private String name;
    private int age;
    private String email;
    private String phone;
    private String birthday;

    @Builder
    public RegisterRequestDto(String id, String password, String name, int age, String email,
        String phone, String birthday) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
    }
}
