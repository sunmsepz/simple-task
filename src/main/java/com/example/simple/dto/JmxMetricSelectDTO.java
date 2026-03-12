package com.example.simple.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;


/**
 * Jmx Metric 수집 확인 DTO
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-11 PM 05:14
 */
@Getter
@Builder
@ToString
public class JmxMetricSelectDTO {

    /** Metric ID */
    private long metricId;
    
    /** Metric 명 */
    private String metricNm;
    
    /** Metric 값 */
    private Double metricVal;
    
    /** Metric 생성 시간 */
    private Timestamp clctDt;
}
