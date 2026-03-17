package com.example.simple.schedule.scheduler;

import com.example.simple.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 스케줄러 제어 Controller
 * 
 * @version 1.0
 * @author sunmsepz
 * @since 2026-03-10 AM 17:46
 */
@RequestMapping("/api/scheduler")
@RestController
@RequiredArgsConstructor
public class SchedulerController {

    private final SchedluerService schedulerService;

    /**
     *
     * @return
     */
    @PostMapping("/start")
    public ApiResponse<String> startScheduler() {
        schedulerService.start();
        return ApiResponse.success(200, "Scheduler Start", null);
    }


    /**
     *
     * @return
     */
    @PostMapping("/stop")
    public ApiResponse<String> stopScheduler() {
        schedulerService.stop();
        return ApiResponse.success(200, "Scheduler Stop", null);
    }
}
