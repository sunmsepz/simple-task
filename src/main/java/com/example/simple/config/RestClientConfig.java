package com.example.simple.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(RestClientProperties.class)
@RequiredArgsConstructor
public class RestClientConfig {

    /** RestClient 설정 객체 */
    private final RestClientProperties restClientProp;

    /**
     * RestClient Bean 객체 생성
     *
     * @return RestClient Bean 객체
     */
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