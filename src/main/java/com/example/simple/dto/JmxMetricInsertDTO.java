package com.example.simple.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * JmxMetric 저장 DTO
 *
 * @author sunms
 * @version 1.0
 * @since 2026-03-04 오후 3:07
 */
@Data
public class JmxMetricInsertDTO {

    /** Metric 명 */
    private String metricNm;

    /** Metric 값 */
    private Double metricVal;

    /** Metric 생성 시간 */
    private Timestamp clctDt;
}
