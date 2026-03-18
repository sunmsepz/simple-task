package com.example.simple.config;


import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "restclient.properties")
public class RestClientProperties {

    /** 연결 시도 최대 3초 */
    private int connTimeout;

    /** 데이터를 받을 최대 10초 */
    private int readTimeout;
}
