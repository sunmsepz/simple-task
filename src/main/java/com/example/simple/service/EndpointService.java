package com.example.simple.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

/**
 * 엔드포인트에서 Metric 전체 정보 수집
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-09 AM 10:23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EndpointService {

    private final RestClient restClient;

    @Value("${kafka.metrics.endpoint}")
    private String endpointUrl;

    /**
     * EndpointUrl에서 Kafka Metrics 문자열 수집
     *
     * @return Kafka Metrics 문자열 정보
     * @throws
     */
    public String collect() {

        // endpointUrl 값 있는지 확인
        if (endpointUrl == null || endpointUrl.isBlank()) {
            log.warn("Kafka Metric Endpoint URL is blank");
            return null;
        }
        
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
        } catch (Exception e) {
            // 그 외 모든 에러
            log.error("Endpoint URL collection failed : ", e);
            return null;
        }
    }
}
