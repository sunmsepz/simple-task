package com.example.simple.scheduling.metric.read;

import com.example.simple.scheduling.metric.read.dto.MetricReadDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Metric 조회 Mapper 인터페이스
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-17 PM 05:57
 */
@Mapper
public interface MetricReadMapper {

    /**
     * Kafka Metric의 전체 데이터 목록 조회
     *
     * @return MetricReadDTO 목록
     */
    List<MetricReadDTO> findAll() throws Exception;
}
