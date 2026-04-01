package com.example.simple.scheduling.metric.collect;

import com.example.simple.scheduling.metric.collect.dto.MetricCollectDTO;
import com.example.simple.common.restclient.RestClientData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

/**
 * kafkaMetrics 관련 Service
 *
 * @author sunmsepz
 * @version 1.2
 * @since 2026-03-17 PM 06:07
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MetricCollectService {

    /** Jmx Mapper 객체 */
    private final MetricCollectMapper metricCollectMapper;

    /** RestClientData 객체 */
    private final RestClientData restClientData;

    /**
     * EndpointUrl에서 Kafka Metrics 문자열 수집
     *
     * @return Kafka Metrics 문자열 정보
     * @throws ResourceAccessException RestClient 연결 시도 에러
     * @throws RestClientResponseException RestClient 응답 에러
     */
    public Double collect(String endpointUrl, String metricNm) {
        
        // endpointUrl 값 있는지 확인
        if (endpointUrl == null || endpointUrl.isBlank()) {
            throw new IllegalArgumentException("Kafka Metric Endpoint URL is no value : " + endpointUrl);
        }

        // metricNm 값 있는지 확인
        if (metricNm == null || metricNm.isBlank()) {
            throw new IllegalArgumentException("Metric Name is no value : " + metricNm);
        }

        String data = restClientData.getString(endpointUrl);

        // data 값 있는지 확인
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException("Kafka Data is null : " + data);
        }

        Double metricVal = parseMetric(data, metricNm);

        // 추출한 값 있는지 확인
        if (metricVal == null) {
            throw new IllegalArgumentException("metricVal is null : " + metricVal);
        }

        return metricVal;
    }

    /**
     * KafkaMetricLines에서 추출할 Metric의 값을 반환
     * 
     * @param kafkaMetrics kafka 매트릭 값들이 담긴 문자열
     * @param metricNm 추출할 Metric명
     * @return Double형의 metric 값
     * @throws NumberFormatException 파싱이 Double 안될 때
     */
    private Double parseMetric(String kafkaMetrics, String metricNm) {

        // 추출할 Metric 명칭 확인
        if (kafkaMetrics == null || kafkaMetrics.isBlank()) {
            throw new IllegalArgumentException("Kafka Metrics is no value : " + kafkaMetrics);
        }

        // 추출할 Metric 명칭 확인
        if (metricNm == null || metricNm.isBlank()) {
            throw new IllegalArgumentException("Metric Name is no value : " + metricNm);
        }

        Double jmxVal = null;

        for (String metric : kafkaMetrics.split("\n")) {

            // 주석 문장 제외
            if (metric.startsWith("#")) continue;

            if (metric.startsWith(metricNm)) {
                try {
                    String jmxScrDurSec = metric.substring(metric.lastIndexOf(" ") + 1);
                    jmxVal = Double.parseDouble(jmxScrDurSec);
                    break;
                } catch(NumberFormatException e) {
                    log.error("jmxVal is error : {}", e);
                    return null;
                }
            }
        }

        return jmxVal;
    }

    /**
     * 추출한 JmxMetric 검증 및 저장
     *
     * @param jmxDTO JmxMetric의 추출 정보 DTO
     */
    @Transactional
    public void save(MetricCollectDTO jmxDTO) throws Exception {

        if (jmxDTO == null) {
            throw new IllegalArgumentException("jmxDTO parameter must not be null");
        }

//        metricCollectMapper.insert(jmxDTO);
        log.info("jmxDTO : {}", jmxDTO); // 디버깅 용 츨력

        // Kafka 사용 시, Outbox 패턴
        // 데이터 정합성을 위해 Outbox Table 데이터 저장(Pending)
        // Relay로 Pending 이벤트 감지 후, Kafka 전송
        // 성공 시, SENT로 상태 업데이트
        // 실패 시, FAILED으로 변경 -> 다음 주기 PENDING 변경
    }
}
