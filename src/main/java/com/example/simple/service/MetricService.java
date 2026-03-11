package com.example.simple.service;

import com.example.simple.dto.JmxMetricInsertDTO;
import com.example.simple.mapper.JmxMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * kafkaMetrics에서 특정 metric 추출 Service
 *
 * @author sunmsepz
 * @version 1.1
 * @since 2026-03-09 PM 05:25
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MetricService {

    private final JmxMapper jmxMapper;

    /**
     * KafkaMetricLines에서 추출할 Metric의 값을 반환
     * 
     * @param kafkaMetrics kafka 매트릭 값들이 담긴 문자열
     * @param metricNm 추출할 Metric명
     * @return Double형의 metric 값
     * @throws NumberFormatException 파싱이 Double 안될 때
     */
    public Double parseMetric(String kafkaMetrics, String metricNm) throws Exception {

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
     * 추출한 JmxMetric 검증 및 저장 메소드 호출
     *
     * @param jmxDTO JmxMetric의 추출 정보 DTO
     */
    @Transactional
    public void saveJmxMetric(JmxMetricInsertDTO jmxDTO) throws Exception {

        if (jmxDTO == null) {
            throw new IllegalArgumentException("jmxDTO parameter must not be null");
        }

        log.info("jmxDTO : {}", jmxDTO); // 디버깅 용 츨력
        save(jmxDTO);
    }

    /**
     * 추출한 JmxMetric 정보 DB 저장
     *
     * @param jmxDTO JmxMetric의 추출 정보 DTO
     */
    private void save(JmxMetricInsertDTO jmxDTO) throws Exception {
//        jmxMapper.insert(jmxDTO);
    }

}
