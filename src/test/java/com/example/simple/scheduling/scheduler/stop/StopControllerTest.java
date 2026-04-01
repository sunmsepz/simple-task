package com.example.simple.scheduling.scheduler.stop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * SchedulerStop 테스트
 *
 * @author sunmsepz
 * @version 1.0
 * @since 2026-03-23 AM 11:49
 */
@WebMvcTest(StopController.class)
class StopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StopService stopService;


    @Test
    @DisplayName("이미 중지 중인 상태에서 스케줄러 중지 테스트")
    void schedulerAlreadyStoppedTest() throws Exception {

        // given
        given(stopService.stop()).willReturn("Scheduler has already stopped");

        // when & then
        mockMvc.perform(post("/api/scheduler/stop"))
                .andExpect(status().isOk())
                .andExpect(content().string("Scheduler has already stopped"));
    }

    @Test
    @DisplayName("스케줄러 중지 테스트")
    void stopSchedulerTest() throws Exception {

        // given
        given(stopService.stop()).willReturn("stop Scheduler");

        // when & then
        mockMvc.perform(post("/api/scheduler/stop"))
                .andExpect(status().isOk())
                .andExpect(content().string("stop Scheduler"));
    }

}