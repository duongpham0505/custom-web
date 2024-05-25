package com.dev.custom.controller;

import com.dev.custom.service.KeyCloakServiceImpl;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
public class TestController {
    @Autowired
    KeyCloakServiceImpl keyCloakService;


    @PostConstruct
    public void getUserKeycloak() {
        List<UserRepresentation> users = keyCloakService.getUsers();
        System.out.println(users);
    }
}
