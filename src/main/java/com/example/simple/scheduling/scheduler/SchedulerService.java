package com.example.simple.scheduling.scheduler;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 스케줄러 제어 Service
 *
 * @version 1.0
 * @author sunmsepz
 * @since 2026-03-18 PM 03:06
 */
@Service
@Slf4j
public class SchedulerService {

    /** 스케줄링 제어 flag */
    @Getter
    @Setter
    private volatile boolean running = false;

    /**
     * Scheduler 로직 실행
     */
    public void start() {
        if (this.isRunning()) {
            log.info("Scheduler has already started");
            return;
        }

        this.setRunning(true);
        log.info("start Scheduler");
    }

    /**
     * Scheduler 로직 중단
     */
    public void stop() {
        if (this.isRunning() == false) {
            log.info("Scheduler has already stopped");
            return;
        }

        this.setRunning(false);
        log.info("stop Scheduler");
    }
}
