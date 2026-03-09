package com.example.simple.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JmxMetricInsertDTO {

    /** Metric 명 */
    private String metricNm;

    /** Metric 값 */
    private Double metricVal;

    /** Metric 생성 시간 */
    private Timestamp clctDt;

}
