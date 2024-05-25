package com.dev.custom.service.port;

import com.dev.custom.service.data.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO addUser(UserDTO userDto);
    UserDTO updateUser(UserDTO userDto);
    UserDTO getUser(long userId);
    List<UserDTO> getUsers();
    void deleteUser(long userId);
    void activeUser(long userid);
}
