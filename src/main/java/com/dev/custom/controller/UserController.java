package com.dev.custom.controller;

import com.dev.custom.service.data.dto.UserDTO;
import com.dev.custom.service.port.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("users")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("users")
    public UserDTO addUser(@RequestBody UserDTO userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping("user/{id}")
    public UserDTO getUser(@PathVariable("id") long userId) {
        return userService.getUser(userId);
    }
}
