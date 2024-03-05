package com.dev.custom.configuration;

import com.cloudinary.Cloudinary;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FireBaseConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(FireBaseConfig.class);
    private static final String CLOUD_NAME = "dcmaoru0t";
    private static final String API_KEY = "155653711885377";
    private static final String API_SECRET = "XKc0FR9SZeKYShqYXplHl14C96c";
    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name",CLOUD_NAME);
        config.put("api_key",API_KEY);
        config.put("api_secret",API_SECRET);

        return new Cloudinary(config);
    }

//    @Bean
//    public FirebaseApp firebaseApp() throws IOException {
//        ClassLoader classLoader = getClass().getClassLoader();
//        InputStream inputStream = classLoader.getResourceAsStream("firebase/firebase-config.json");
//        // Load tệp service account key
//        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
//
//        // Khởi tạo FirebaseApp với credentials
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(credentials)
//                .build();
//        return FirebaseApp.initializeApp(options);
//    }
}
