package com.example.simple.metric;


import com.example.simple.metric.dto.JmxMetricSelectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class MetricController {

    private final MetricService metricService;

    @GetMapping("/metrics")
    public List<JmxMetricSelectDTO> metrics() throws Exception {
        return metricService.findAll();
    }
}
