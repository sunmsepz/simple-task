package com.example.simple.config;

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
public class RestClientConfig {

    private static final int CONN_TMOUT = 3000;
    private static final int RD_TMOUT = 10000;

    @Bean
    public RestClient restClient() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 연결 시도 제한 시간
        requestFactory.setConnectTimeout(CONN_TMOUT);
        // 데이터를 받을 제한 시간
        requestFactory.setReadTimeout(RD_TMOUT);

        return RestClient.builder()
                .requestFactory(requestFactory)
                .build();
    }
}