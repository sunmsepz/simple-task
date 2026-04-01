package com.example.simple.scheduling.scheduler.start;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * SchedulerStart 테스트
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-23 AM 11:49
 */
@WebMvcTest(StartController.class)
class StartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StartService startService;


    @Test
    @DisplayName("이미 실행 중인 상태에서 스케줄러 시작 테스트")
    void schedulerAlreadyRunningTest() throws Exception {

        // given
        given(startService.start()).willReturn("Scheduler has already started");

        // when & then
        mockMvc.perform(post("/api/scheduler/start"))
                .andExpect(status().isOk())
                .andExpect(content().string("Scheduler has already started"));
    }

    @Test
    @DisplayName("스케줄러 시작 테스트")
    void startSchedulerTest() throws Exception {

        // given
        given(startService.start()).willReturn("start Scheduler");

        // when & then
        mockMvc.perform(post("/api/scheduler/start"))
                .andExpect(status().isOk())
                .andExpect(content().string("start Scheduler"));
    }
}