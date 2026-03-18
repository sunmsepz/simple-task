package com.example.simple.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * RestClient 설정 파라미터 값
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-18 AM 10:42
 */
@ConfigurationProperties(prefix = "restclient.properties")
@Getter
@Setter
public class RestClientProperties {

    /** 연결 시도 최대 3초 */
    private int connTimeout;

    /** 데이터를 받을 최대 10초 */
    private int readTimeout;
}
