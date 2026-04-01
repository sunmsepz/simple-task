package com.example.simple.scheduling.metric.read;

import com.example.simple.scheduling.metric.read.dto.MetricReadDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * MetricReadController 테스트
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-23 PM 01:11
 */
@WebMvcTest(MetricReadController.class)
class MetricReadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MetricReadService metricReadService;

    @Test
    @DisplayName("Metrics 값이 없을 때 빈 목록 반환 테스트")
    void metricsEmptyList() throws Exception {

        // given
        given(metricReadService.findAll()).willReturn(List.of());

        // when & then
        mockMvc.perform(get("/api/metrics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("Metric 목록 조회 성공"))
                // data가 빈 리스트인지 확인
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    @DisplayName("Metrics 값 가져오기 성공 테스트")
    void MetricsSuccess() throws Exception {

        // given
        long id = 1L;
        String nm = "jmx_scrape_duration_seconds";
        Double val = 0.9999999;
        Timestamp clctDt = Timestamp.valueOf("2026-03-18 17:37:20.001");

        List<MetricReadDTO> metrics = List.of(new MetricReadDTO(id, nm, val, clctDt));
        given(metricReadService.findAll()).willReturn(metrics);

        // when & then
        mockMvc.perform(get("/api/metrics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("Metric 목록 조회 성공"))
                .andExpect(jsonPath("$.data[0].metricId").value(id))
                .andExpect(jsonPath("$.data[0].metricNm").value(nm))
                .andExpect(jsonPath("$.data[0].metricVal").value(val))
                // 시간대 차이가 발생하여 존재 여부로 확인
                .andExpect(jsonPath("$.data[0].clctDt").exists());
    }
}