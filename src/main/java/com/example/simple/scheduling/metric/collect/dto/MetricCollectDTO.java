package com.example.simple.scheduling.metric.collect.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * JmxMetric 저장 DTO
 *
 * @author sunmsepz
 * @version 1.1
 * @since 2026-03-04 PM 3:07
 */
@Getter
@ToString
@AllArgsConstructor
@Builder
public class MetricCollectDTO {

    /** Metric 명 */
    private String metricNm;

    /** Metric 값 */
    private Double metricVal;

    /** Metric 생성 시간 */
    private Timestamp clctDt;

    /**
     * JmxMetricInsertDTO의 입력값 검증한 DTO 반환
     *
     * @param metricNm Metric 명
     * @param metricVal Metric의 값
     * @param clctDt Metric 추출 시간
     * @return JmxMetricInsertDTO
     */
    public static MetricCollectDTO of(String metricNm, Double metricVal, Timestamp clctDt) {
        /** DTO 입력값 검증 */
        if (metricNm == null || metricNm.isBlank()) {
            throw new IllegalArgumentException("metricNm is empty");
        }

        if (metricVal == null) {
            throw new IllegalArgumentException("metricVal is null");
        }

        if (clctDt == null) {
            throw new IllegalArgumentException("clctDt is null");
        }

        return MetricCollectDTO.builder()
                                 .metricNm(metricNm)
                                 .metricVal(metricVal)
                                 .clctDt(clctDt)
                                 .build();
    }
}
