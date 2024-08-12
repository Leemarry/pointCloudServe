package com.bear.reseeding.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioClientConfig {

    private static final String ENDPOINT = "play.min.io";
    private static final String ACCESS_KEY = "YOUR-ACCESSKEY";
    private static final String SECRET_KEY = "YOUR-SECRETKEY";

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(ENDPOINT)
                .credentials(ACCESS_KEY, SECRET_KEY)
                .build();
    }
}

//// 使用方式
//@Autowired
//private MinioClient minioClient;
// 然后使用minioClient进行操作