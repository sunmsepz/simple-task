package com.example.simple.service;

import com.example.simple.dto.JmxMetricInsertDTO;
import com.example.simple.mapper.JmxMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * KafkaMetrics에서 특정 metric 추출 Service
 *
 * @author sunmsepz
 * @version 1.1
 * @since 2026-03-09 오후 05:25
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MetricService {

    private final JmxMapper jmxMapper;

    /**
     * KafkaMetricLines에서 추출할 Metric의 값을 반환
     * 
     * @param kafkaMetricLines kafka Metrics 정보가 담긴 문자열
     * @param metricNM 추출할 Metric명
     * @return Double형의 metric 값
     * @throws NumberFormatException 파싱이 Double 안될 때
     */
    public Double parseMetric(String kafkaMetricLines, String metricNM) {

        // 추출할 Metric 명칭 확인
        if (metricNM == null || metricNM.isBlank()) {
            log.warn("Metric Name is empty");
            return null;
        }

        Double jmxVal = null;

        for (String line : kafkaMetricLines.split("\n")) {

            // 주석 문장 제외
            if (line.startsWith("#")) continue;

            if (line.startsWith(metricNM)) {
                try {
                    String jmxScrDurSec = line.substring(line.lastIndexOf(" ") + 1);
                    jmxVal =  Double.parseDouble(jmxScrDurSec);
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
     * 추출한 JmxMetric 정보 DB 저장
     *
     * @param jmxDTO JmxMetric의 추출 정보 DTO
     */
    @Transactional
    public void save(JmxMetricInsertDTO jmxDTO) {
        jmxMapper.insert(jmxDTO);
    }

}
