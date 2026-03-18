package com.example.simple.scheduling.scheduler;

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
@RestController
@RequestMapping("/api/scheduler")
@RequiredArgsConstructor
public class SchedulerController {

    /** 스케줄러 서비스 객체 */
    private final SchedulerService schedulerService;

    /**
     * 스케줄러 시작
     *
     * @return
     */
    @PostMapping("/start")
    public ApiResponse<String> startScheduler() {

        schedulerService.start();

        return ApiResponse.success(200, "Scheduler Start", null);
    }


    /**
     * 스케줄러 종료
     *
     * @return
     */
    @PostMapping("/stop")
    public ApiResponse<String> stopScheduler() {

        schedulerService.stop();

        return ApiResponse.success(200, "Scheduler Stop", null);
    }
}
