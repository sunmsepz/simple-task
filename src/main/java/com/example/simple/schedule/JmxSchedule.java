package com.example.simple.schedule;

import com.example.simple.metric.dto.JmxMetricInsertDTO;
import com.example.simple.schedule.scheduler.EndpointService;
import com.example.simple.metric.MetricService;
import com.example.simple.schedule.scheduler.SchedluerService;
import lombok.RequiredArgsConstructor;
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
public class JmxSchedule {

    /** Endpoint 서비스 객체 */
    private final EndpointService endpointService;

    /** Metric 서비스 객체 */
    private final MetricService metricService;

    /** Scheduler 서비스 객체 */
    private final SchedluerService schedulerService;

    // 추출할 특정 Metric 명
    private static final String METRIC_NM = "jmx_scrape_duration_seconds";


    /**
     * Kafka Metric에서 jmx_scrape_duration_seconds 파싱 <br>
     * 20초마다 스케줄링 실행
     * 
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
            String kafkaMetrics = endpointService.collect();

            // 수집한 metrics에서 항목 값 추출
            Double jmxVal = metricService.parseMetric(kafkaMetrics, METRIC_NM);

            // 각각의 구한 값을 DTO 변환(입력값 검증)
            JmxMetricInsertDTO jmxDTO = JmxMetricInsertDTO.of(METRIC_NM, jmxVal, collectTime);

            // JmxDTO 저장
            metricService.save(jmxDTO);

        } catch (Exception e) {
            log.error("Scheduler failed", e);
        }
    }
}
