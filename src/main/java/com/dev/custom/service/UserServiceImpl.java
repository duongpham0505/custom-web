package com.dev.custom.service;

import com.dev.custom.repository.UserRepository;
import com.dev.custom.service.data.dto.UserDTO;
import com.dev.custom.service.data.entity.UserEntity;
import com.dev.custom.service.port.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public UserDTO addUser(UserDTO userDto) {
        UserEntity user = modelMapper.map(userDto, UserEntity.class);
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto) {
        return null;
    }

    @Override
    public UserDTO getUser(long userId) {
        UserEntity user = userRepository.getById(userId);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(long userId) {

    }

    @Override
    public void activeUser(long userid) {

    }
}
