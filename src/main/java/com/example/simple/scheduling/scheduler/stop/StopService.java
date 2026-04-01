package com.example.simple.scheduling.scheduler.stop;

import com.example.simple.scheduling.ScheduleFlag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 스케줄러 중지 Service
 *
 * @version 1.0
 * @author sunmsepz
 * @since 2026-03-25 PM 03:16
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StopService {

    /** 스케줄링 제어 flag */
    private final ScheduleFlag scheduleFlag;

    /**
     * Scheduler 로직 중지
     *
     * @return 중지 상태 메시지
     */
    public String stop() {

        // 이미 중지일 경우, 변동 없음
        if (scheduleFlag.isRunning() == false) {
            log.info("Scheduler has already stopped");
            return "Scheduler has already stopped";
        }

        scheduleFlag.stop();
        log.info("stop Scheduler");
        return "stop Scheduler";
    }
}
