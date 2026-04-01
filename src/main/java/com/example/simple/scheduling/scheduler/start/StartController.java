package com.example.simple.scheduling.scheduler.start;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 스케줄러 시작 Controller
 *
 * @version 1.0
 * @author sunmsepz
 * @since 2026-03-25 PM 03:06
 */
@RestController
@RequestMapping("/api/scheduler")
@RequiredArgsConstructor
public class StartController {

    /** 스케줄러 서비스 객체 */
    private final StartService startService;

    /**
     * 스케줄러 시작
     *
     * @return 스케줄러 시작 상태 메시지
     */
    @PostMapping("/start")
    public ResponseEntity<String> startScheduler() {

        String msg = startService.start();
        return ResponseEntity.ok(msg);
    }
}
