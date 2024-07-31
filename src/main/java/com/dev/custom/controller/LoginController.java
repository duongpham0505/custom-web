package com.dev.custom.controller;

import com.dev.custom.service.data.dto.UserLoginRequestDTO;
import com.dev.custom.service.data.response.Response;
import com.dev.custom.service.port.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<Response<Object>> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {

        return ResponseEntity.ok(loginService.login(userLoginRequestDTO));
    }
}
