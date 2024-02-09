package com.jeans.cosmetic_project.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {

    private String id;
    private String password;
    private String name;
    private String email;
    private String phone;
}
