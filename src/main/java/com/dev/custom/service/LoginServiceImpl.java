package com.dev.custom.service;

import com.dev.custom.service.base.user.CustomUserDetails;
import com.dev.custom.service.base.user.JwtTokenProvider;
import com.dev.custom.service.data.dto.UserLoginRequestDTO;
import com.dev.custom.service.data.response.Response;
import com.dev.custom.service.port.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public Response<Object> login(UserLoginRequestDTO userLoginRequestDTO) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequestDTO.getUserName(),
                        userLoginRequestDTO.getPassword()
                )
        );
        String token = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return null;
    }
}
