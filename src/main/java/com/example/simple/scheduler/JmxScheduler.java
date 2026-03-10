package com.example.simple.scheduler;

import com.example.simple.dto.JmxMetricInsertDTO;
import com.example.simple.service.EndpointService;
import com.example.simple.service.MetricService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
@Slf4j
public class JmxScheduler {

    private final EndpointService endpointService;
    private final MetricService metricService;

    // 추출할 특정 Metric 명
    private static final String METRIC_NM = "jmx_scrape_duration_seconds";

    /**
     * Kafka Metric에서 jmx_scrape_duration_seconds 파싱 <br>
     * 20초마다 스케줄링 실행
     * 
     */
    @Scheduled(cron = "0/20 * * * * *")
    public void scheduler() {
        try {
            long collectTime = System.currentTimeMillis();

            // Endpoint에서 kafka metrics 수집
            String kafkaMetrics = endpointService.collect();

            // 수집한 metrics에서 항목 값 추출
            Double jmxVal = metricService.parseMetric(kafkaMetrics, METRIC_NM);

            // 각각의 구한 값을 DTO 변환(입력값 검증)
            JmxMetricInsertDTO jmxDTO = JmxMetricInsertDTO.of(METRIC_NM, jmxVal, new Timestamp(collectTime));

            // JmxDTO 저장
            metricService.saveJmxMetric(jmxDTO);

        } catch (Exception e) {
            log.error("Scheduler failed", e);
        }
    }
}
