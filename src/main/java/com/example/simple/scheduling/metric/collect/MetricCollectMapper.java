package com.example.simple.scheduling.metric.collect;

import com.example.simple.scheduling.metric.collect.dto.MetricCollectDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Metric 저장 Mapper 인터페이스
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-17 PM 05:57
 */
@Mapper
public interface MetricCollectMapper {

    /** JmxMetric 정보 저장
     *
     * @param jmxDTO JmxMetric의 추출 정보 DTO
     */
    void insert(MetricCollectDTO jmxDTO) throws Exception;
}
