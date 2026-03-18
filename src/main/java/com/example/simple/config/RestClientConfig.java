package com.example.simple.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

/**
 * RestClient Bean 설정 클래스
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-09 PM 3:12
 */
@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    private final RestClientProperties restClientProp;

    @Bean
    public RestClient restClient() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 연결 시도 제한 시간
        requestFactory.setConnectTimeout(restClientProp.getConnTimeout());
        // 데이터를 받을 제한 시간
        requestFactory.setReadTimeout(restClientProp.getReadTimeout());

        return RestClient.builder()
                .requestFactory(requestFactory)
                .build();
    }
}