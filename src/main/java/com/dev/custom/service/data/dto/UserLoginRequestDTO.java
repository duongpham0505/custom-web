package com.dev.custom.service.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginRequestDTO {
    private String userName;
    private String email;
    private String password;
    private String captcha;
}
