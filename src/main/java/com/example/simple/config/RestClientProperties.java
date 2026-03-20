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

    /** 연결 시도 제한 시간 */
    private int connTimeout;

    /** 응답 데이터 받는 제한 시간 */
    private int readTimeout;

    /** 연결 풀 최대 연결 수 */
    private int maxConnTotal;

    /** 호스트당 풀 최대 연결 수 */
    private int maxConnPerRoute;

    /** 재시도 횟수 */
    private int retryCount;

    /** 재시도 간격 (밀리초) */
    private long retryDelay;
}
