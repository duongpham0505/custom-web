package com.dev.custom.service;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeyCloakServiceImpl {

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

//    public List<UserRepresentation> getUsers() {
//        Keycloak keycloak = KeycloakBuilder.builder()
//                .serverUrl(serverUrl)
//                .realm(realm)
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .grantType("client_credentials")
//                .build();
//
//        RealmResource realmResource = keycloak.realm(realm);
//        UsersResource usersResource = realmResource.users();
//
//        // Get list of users
//        List<UserRepresentation> users = usersResource.list();
//
//        keycloak.close();
//
//        return users;
//    }

    public List<UserRepresentation> getUsers() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType("client_credentials")
                .build();

        try {
            RealmResource realmResource = keycloak.realm(realm);
            UsersResource usersResource = realmResource.users();
            List<UserRepresentation> users = usersResource.list();
            return users;
        } finally {
            keycloak.close();
        }
    }
}
