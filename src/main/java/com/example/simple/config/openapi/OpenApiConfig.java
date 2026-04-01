package com.example.simple.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenApi 설정 객체
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-23 AM 10:33
 */
@Configuration
public class OpenApiConfig {

    /**
     * OpenAPI Bean 객체
     *
     * @return OpenAPI 객체
     */
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("간단 Spring Boot 과제 API")
                        .version("1.0.0")
                        .description("간단 과제 API 문서 설명"));
    }

}
