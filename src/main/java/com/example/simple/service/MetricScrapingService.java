package com.example.simple.service;

import com.example.simple.dto.JmxMetricInsertDTO;
import com.example.simple.mapper.JmxMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

/**
 * KafkaMetrics에서 특정 metric 추출 Service
 *
 * @author sunms
 * @version 1.0
 * @since 2026-03-04 오후 4:21
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MetricScrapingService {

    private final JmxMapper jmxMapper;

    // 추출할 특정 Metric 명
    private static final String METRIC_NM = "jmx_scrape_duration_seconds";

    @Value("${kafka.metrics.endpoint}")
    private String endpointUrl;

    /**
     * Kafka Metric에서 jmx_scrape_duration_seconds 파싱 <br>
     * 20초마다 메소드 실행
     *
     * @throws NumberFormatException 파싱이 Double이 안될 시
     */
    @Scheduled(fixedRate = 20000)
    public void parseMetric() {
        String kafkaMetric = collectKafkaMetric();

        if (kafkaMetric == null) {
            log.warn("kafkaMetric is null");
            return;
        }

        long collectTime = System.currentTimeMillis();
        Double jmxVal = null;

        for (String line : kafkaMetric.split("\n")) {

            // 주석 문장 제외
            if (line.startsWith("#")) continue;

            if (line.startsWith(METRIC_NM)) {
                try {
                    String jmxScrDurSec = line.substring(line.lastIndexOf(" ") + 1);
                    jmxVal = Double.parseDouble(jmxScrDurSec);
                    break;
                } catch(NumberFormatException e) {
                    log.error("jmxVal is error : {}", e);
                    return;
                }
            }
        }

        if (jmxVal == null) {
            log.warn("jmxVal is null");
            return;
        }

        // 각각의 구한 값을 DTO 변환
        JmxMetricInsertDTO jmxDTO = new JmxMetricInsertDTO();
        jmxDTO.setMetricNm(METRIC_NM);
        jmxDTO.setMetricVal(jmxVal);
        jmxDTO.setClctDt(new Timestamp(collectTime));

        save(jmxDTO);
    }

    /**
     * 엔드포인트에서 Metric 전체 정보 수집
     *
     * @return kafka metrics 문자열
     */
    private String collectKafkaMetric() {
        RestTemplate restTemplate = new RestTemplate();
        String metricResponse = restTemplate.getForObject(endpointUrl, String.class);

        return metricResponse;
    }

    /**
     * 추출한 JmxMetric 정보 DB 저장
     *
     * @param jmxDTO JmxMetric의 추출 정보 DTO
     */
    @Transactional
    public void save(JmxMetricInsertDTO jmxDTO) {
        log.info("jmxDTO : {}", jmxDTO);
        jmxMapper.insert(jmxDTO);
    }

}
