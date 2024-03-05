package com.dev.custom.service.port;

import com.dev.custom.service.data.response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileCloudinary {
    Response<Object> uploadFile(MultipartFile multipartFile) throws IOException;

    boolean deleteImageFile(String id) throws IOException;
}
