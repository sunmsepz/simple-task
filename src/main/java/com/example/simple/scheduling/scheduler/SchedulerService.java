//package com.example.simple.scheduling.scheduler;
//
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
///**
// * 스케줄러 제어 Service
// *
// * @version 1.0
// * @author sunmsepz
// * @since 2026-03-18 PM 03:06
// */
//@Service
//@Slf4j
//public class SchedulerService {
//
//    /** 스케줄링 제어 flag */
////    @Getter
////    @Setter
////    private volatile boolean running = false;
//
//    /**
//     * Scheduler 로직 시작
//     *
//     * @return 시작 상태 메시지
//     */
//    public String start() {
//
//        // 이미 시작한 경우, 변동 없음
//        if (this.isRunning()) {
//            log.info("Scheduler has already started");
//            return "Scheduler has already started";
//        }
//
//        this.setRunning(true);
//        log.info("start Scheduler");
//        return "start Scheduler";
//    }
//
//    /**
//     * Scheduler 로직 중지
//     *
//     * @return 중지 상태 메시지
//     */
//    public String stop() {
//
//        // 이미 중지일 경우, 변동 없음
//        if (this.isRunning() == false) {
//            log.info("Scheduler has already stopped");
//            return "Scheduler has already stopped";
//        }
//
//        this.setRunning(false);
//        log.info("stop Scheduler");
//        return "stop Scheduler";
//    }
//}
