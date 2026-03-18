package com.example.simple.scheduling;

import com.example.simple.scheduling.metric.collect.dto.MetricCollectDTO;
import com.example.simple.scheduling.metric.collect.MetricCollectService;
import com.example.simple.scheduling.scheduler.SchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
@Slf4j
@RequiredArgsConstructor
public class JmxSchedule {

    /** Metric 서비스 객체 */
    private final MetricCollectService metricCollectService;

    /** Scheduler 서비스 객체 */
    private final SchedulerService schedulerService;

    /** 추출할 특정 Metric 명 */
    private static final String METRIC_NM = "jmx_scrape_duration_seconds";

    /** kafka Metrics 수집 Endpoint */
    @Value("${kafka.metrics.endpoint}")
    private String endpointUrl;

    /**
     * Kafka Metric에서 jmx_scrape_duration_seconds 파싱 <br>
     * 20초마다 스케줄링 실행
     */
    @Scheduled(cron = "0/20 * * * * *")
    public void scheduler() {

        Timestamp collectTime = new Timestamp(System.currentTimeMillis());

        if (schedulerService.isRunning() == false) {
            log.info("Scheduler off - [collectTime : {}]", collectTime);
            return;
        }

        try {
            // Endpoint에서 kafka metrics 수집
            String kafkaMetrics = metricCollectService.collect(endpointUrl);

            // 수집한 metrics에서 항목 값 추출
            Double jmxVal = metricCollectService.parseMetric(kafkaMetrics, METRIC_NM);

            // 각각의 구한 값을 DTO 변환(입력값 검증)
            MetricCollectDTO jmxDTO = MetricCollectDTO.of(METRIC_NM, jmxVal, collectTime);

            // JmxDTO 저장
            metricCollectService.save(jmxDTO);

        } catch (Exception e) {
            log.error("Scheduler failed", e);
        }
    }
}
