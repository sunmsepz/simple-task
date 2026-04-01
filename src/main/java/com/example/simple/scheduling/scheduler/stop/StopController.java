package com.example.simple.scheduling.scheduler.stop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 스케줄러 중지 Controller
 *
 * @version 1.0
 * @author sunmsepz
 * @since 2026-03-25 PM 03:16
 */
@RestController
@RequestMapping("/api/scheduler")
@RequiredArgsConstructor
public class StopController {

    /** 스케줄러 서비스 객체 */
    private final StopService stopService;

    /**
     * 스케줄러 중지
     *
     * @return 스케줄러 중지 상태 메시지
     */
    @PostMapping("/stop")
    public ResponseEntity<String> stopScheduler() {

        String msg = stopService.stop();
        return ResponseEntity.ok(msg);
    }
}
