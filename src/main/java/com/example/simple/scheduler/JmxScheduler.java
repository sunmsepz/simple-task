package com.example.simple.scheduler;

import com.example.simple.dto.JmxMetricInsertDTO;
import com.example.simple.service.EndpointService;
import com.example.simple.service.MetricService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Jmx 매트릭 스케줄링 클래스
 * 
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-10 PM 03:53
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JmxScheduler {

    /** Endpoint 서비스 객체 */
    private final EndpointService endpointService;

    /** Metric 서비스 객체 */
    private final MetricService metricService;

    // 추출할 특정 Metric 명
    private static final String METRIC_NM = "jmx_scrape_duration_seconds";

    // 스케줄링 제어 flag
    @Getter
    @Setter
    private volatile boolean running = false;

    /**
     * Kafka Metric에서 jmx_scrape_duration_seconds 파싱 <br>
     * 20초마다 스케줄링 실행
     * 
     */
    @Scheduled(cron = "0/20 * * * * *")
    public void scheduler() {

        Timestamp collectTime = new Timestamp(System.currentTimeMillis());

        if (this.isRunning() == false) {
            log.info("Scheduler off - [collectTime : {}]", collectTime);
            return;
        }

        try {
            // Endpoint에서 kafka metrics 수집
            String kafkaMetrics = endpointService.collect();

            // 수집한 metrics에서 항목 값 추출
            Double jmxVal = metricService.parseMetric(kafkaMetrics, METRIC_NM);

            // 각각의 구한 값을 DTO 변환(입력값 검증)
            JmxMetricInsertDTO jmxDTO = JmxMetricInsertDTO.of(METRIC_NM, jmxVal, collectTime);

            // JmxDTO 저장
            metricService.saveJmxMetric(jmxDTO);

        } catch (Exception e) {
            log.error("Scheduler failed", e);
        }
    }

    /**
     * Scheduler 로직 실행
     */
    public void start() {
        if (this.isRunning()) {
            log.info("Scheduler has already started");
            return;
        }

        this.setRunning(true);
        log.info("start Scheduler");
    }

    /**
     * Scheduler 로직 중단
     */
    public void stop() {
        if (!this.isRunning()) {
            log.info("Scheduler has already stopped");
            return;
        }

        this.setRunning(false);
        log.info("stop Scheduler");
    }
}
