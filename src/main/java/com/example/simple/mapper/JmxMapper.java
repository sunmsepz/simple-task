package com.example.simple.mapper;

import com.example.simple.dto.JmxMetricInsertDTO;
import com.example.simple.dto.JmxMetricSelectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    void insert(JmxMetricInsertDTO jmxDTO) throws Exception;

    /**
     * JmxMetric의 수집 데이터들 반환
     */
    List<JmxMetricSelectDTO> findAll() throws Exception;
}
