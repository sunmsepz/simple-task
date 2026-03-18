package com.example.simple.scheduling.metric.read;

import com.example.simple.scheduling.metric.read.dto.MetricReadDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Kafka Metric 조회 Service
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-17 PM 05:57
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MetricReadService {

    /** Kafka Metric 조회 Mapper */
    private final MetricReadMapper metricReadMapper;

    /**
     * Kafka Metric 전체 수집 데이터 반환
     *
     * @return Kafka Metric 전체 수집 데이터 목록
     */
    @Transactional(readOnly = true)
    public List<MetricReadDTO> findAll() {

        try {
            List<MetricReadDTO> kafkaMetrics = metricReadMapper.findAll();

            if (kafkaMetrics.isEmpty()) {
                log.info("kafkaMetrics is Empty");
                return List.of();
            }

            log.info("kafkaMetrics is Exist");
            return kafkaMetrics;
        } catch (Exception e) {
            throw new RuntimeException("Jmx Metrics Error : ", e);
        }
    }
}
