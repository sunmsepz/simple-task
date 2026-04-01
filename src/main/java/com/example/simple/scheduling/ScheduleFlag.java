package com.example.simple.scheduling;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * 스케줄링 제어 flag
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-25 PM 01:52
 */
@Component
@Getter
public class ScheduleFlag {

    /**
     * 스케줄링 제어 flag <br>
     * 초기 값은 false로 스케줄링 작동을 안하고, Api를 통해 제어
     */
    private boolean running = false;

    /**
     * 스케줄링 작동으로 변경
     */
    public void start() {
        this.running = true;
    }

    /**
     * 스케줄링 중지로 변경
     */
    public void stop() {
        this.running = false;
    }
}
