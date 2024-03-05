package com.dev.custom.service;

import com.cloudinary.Cloudinary;
import com.dev.custom.service.data.response.Response;
import com.dev.custom.service.port.FileCloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileCloudinary {

    private final Cloudinary cloudinary;

    public FileServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public Response<Object> uploadFile(MultipartFile multipartFile) throws IOException {
        String uId = UUID.randomUUID().toString();
        String urlImage =  cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("`public_id`", uId))
                .get("url")
                .toString();
        Map<String, String> results = new HashMap<>();
        results.put("id", uId);
        results.put("imageUrl", urlImage);
        return Response.builder().object(results).code("200").build();
    }

    @Override
    public boolean deleteImageFile(String id) throws IOException {
        cloudinary.uploader().destroy(id, new HashMap<>());
        return true;
    }
}
