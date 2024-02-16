package com.dev.custom.service;

import com.dev.custom.service.data.dto.Image;
import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class FileServiceImpl {

    public String saveImage(MultipartFile file) throws IOException {
        UUID uuId = UUID.randomUUID();
        Image image = new Image();
        image.setId(uuId.toString());
        image.setFileName(file.getOriginalFilename());
        image.setData(Base64.encodeBase64String(file.getBytes()));
        Gson gson = new Gson();
        String base64 = gson.toJson(image);
        try (FileWriter writer = new FileWriter("L:\\dev-web\\src\\main\\resources\\file\\image.json")) {
            writer.write(base64);
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }
}
