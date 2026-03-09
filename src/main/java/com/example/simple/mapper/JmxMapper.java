package com.example.simple.mapper;

import com.example.simple.dto.JmxMetricInsertDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * JmxMetric 관련 Mapper 인터페이스
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-04 PM 3:09
 */
@Mapper
public interface JmxMapper {

    /** JmxMetric 정보 저장
     *
     * @param jmxDTO JmxMetric의 추출 정보 DTO
     */
    void insert(JmxMetricInsertDTO jmxDTO);

}
