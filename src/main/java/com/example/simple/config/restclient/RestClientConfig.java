package com.example.simple.config.restclient;

import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.util.concurrent.TimeUnit;

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
     * RestClient Bean 객체
     *
     * @return RestClient 객체
     */
    @Bean
    public RestClient restClient() {

        // 연결 Pool 설정
        PoolingHttpClientConnectionManager manager = PoolingHttpClientConnectionManagerBuilder.create()
                // 연결 Pool 최대 연결 수
                .setMaxConnTotal(restClientProp.getMaxConnTotal())
                // 호스트당 최대 연결 수
                .setMaxConnPerRoute(restClientProp.getMaxConnPerRoute())
                .setDefaultConnectionConfig(
                        ConnectionConfig.custom()
                                // 연결 시도 제한 시간
                                .setConnectTimeout(restClientProp.getConnTimeout(), TimeUnit.MILLISECONDS)
                                // 데이터 응답 제한 시간
                                .setSocketTimeout(restClientProp.getReadTimeout(), TimeUnit.MILLISECONDS)
                                .build()
                )
                .build();

        // Http Client 설정
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(manager)
                .setRetryStrategy(new DefaultHttpRequestRetryStrategy(
                        // 재시도 횟수
                        restClientProp.getRetryCount(),
                        // 재시도 간격
                        TimeValue.ofMilliseconds(restClientProp.getRetryDelay())
                ))
                .build();

        return RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory(httpClient))
                .build();
    }
}