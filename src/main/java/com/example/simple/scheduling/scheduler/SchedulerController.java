package com.example.simple.scheduling.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
     * @return 스케줄러 시작 상태 메시지
     */
    @PostMapping("/start")
    public ResponseEntity<String> startScheduler() {

        String msg = schedulerService.start();
        return ResponseEntity.ok(msg);
    }

    /**
     * 스케줄러 중지
     *
     * @return 스케줄러 중지 상태 메시지
     */
    @PostMapping("/stop")
    public ResponseEntity<String> stopScheduler() {

        String msg = schedulerService.stop();
        return ResponseEntity.ok(msg);
    }


}
