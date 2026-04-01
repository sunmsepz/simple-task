package com.example.simple.scheduling.scheduler.start;

import com.example.simple.scheduling.ScheduleFlag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 스케줄러 작동 Service
 *
 * @version 1.0
 * @author sunmsepz
 * @since 2026-03-25 PM 03:06
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StartService {

    /** 스케줄링 제어 flag */
    private final ScheduleFlag scheduleFlag;
    
    /**
     * Scheduler 로직 시작
     *
     * @return 시작 상태 메시지
     */
    public String start() {

        // 이미 시작한 경우, 변동 없음
        if (scheduleFlag.isRunning()) {
            log.info("Scheduler has already started");
            return "Scheduler has already started";
        }

        scheduleFlag.start();
        log.info("start Scheduler");
        return "start Scheduler";
    }
}
