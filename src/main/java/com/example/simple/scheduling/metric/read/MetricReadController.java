package com.example.simple.scheduling.metric.read;


import com.example.simple.common.ApiResponse;
import com.example.simple.scheduling.metric.read.dto.MetricReadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 수집 Metric 조회 컨트롤러
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MetricReadController {

    /** Metric 조회 서비스 */
    private final MetricReadService metricReadService;

    /**
     * Metric 전체 조회
     *
     * @return Metric의 전체 목록
     */
    @GetMapping("/metrics")
    public ApiResponse<List<MetricReadDTO>> metrics() {

        List<MetricReadDTO> metrics = metricReadService.findAll();
        return ApiResponse.success(200, "Metric 목록 조회 성공", metrics);
    }
}
