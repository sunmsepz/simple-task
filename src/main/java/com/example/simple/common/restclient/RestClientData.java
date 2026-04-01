package com.example.simple.common.restclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

/**
 * RestClient를 통해 Endpoint에서 Data 값 가져오기
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-20 PM 03:11
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RestClientData {

    /** RestClient 객체 */
    private final RestClient restClient;

    /**
     * endpointUrl에서 데이터를 문자열로 가져오기
     *
     * @param endpointUrl Endpoint Url
     * @return Endpoint Url에서 가져온 문자열 데이터
     */
    public String getString(String endpointUrl) {

        try {
            return restClient
                    .get()
                    .uri(endpointUrl)
                    .retrieve()
                    .body(String.class);
        } catch (ResourceAccessException e) {
            // RestClient 연결 시도 에러
            log.error("RestClient Connection Error : {}", e.getMessage());
            return null;
        } catch (RestClientResponseException e) {
            // RestClient 응답 에러
            log.error("RestClient Response Error : {}, {}", e.getStatusCode(), e.getMessage());
            return null;
        }
    }
}
