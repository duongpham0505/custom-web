package com.dev.custom.service.port;

import com.dev.custom.service.data.dto.UserLoginRequestDTO;
import com.dev.custom.service.data.response.Response;

public interface LoginService {
    Response<Object> login(UserLoginRequestDTO userLoginRequestDTO);
}
