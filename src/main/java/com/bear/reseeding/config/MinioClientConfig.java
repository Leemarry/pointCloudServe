package com.bear.reseeding.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioClientConfig {

    private static final String ENDPOINT = "play.min.io";
    private static final String ACCESS_KEY = "YOUR-ACCESSKEY";
    private static final String SECRET_KEY = "YOUR-SECRETKEY";

    @Value("${minio.AccessKey}")
    private String AccessKey;
    @Value("${minio.SecretKey}")
    private String SecretKey;
    @Value("${minio.Endpoint}")
    private String Endpoint;

    @Bean
    public MinioClient minioClient() {
        if (AccessKey == null || SecretKey == null || Endpoint == null) {
            throw new IllegalArgumentException("AccessKey, SecretKey, Endpoint cannot be null");
        }
        return MinioClient.builder()
                .endpoint(Endpoint)
                .credentials(AccessKey, SecretKey)
                .build();
    }
}

//// 使用方式
//@Autowired
//private MinioClient minioClient;
// 然后使用minioClient进行操作