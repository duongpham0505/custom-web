package com.dev.custom.service.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    private long id;
    private String userName;
    private String lastName;
    private String firstName;
    private String fullName;
    private String email;
    private int age;
    private String birthDay;
    private String address;
    private String description;
    private String config;
    private String avatar;
}
