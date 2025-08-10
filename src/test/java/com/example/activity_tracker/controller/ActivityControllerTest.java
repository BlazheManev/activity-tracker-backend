package com.example.activity_tracker.controller;

import com.example.activity_tracker.model.Activity;
import com.example.activity_tracker.service.ActivityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles; 
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class ActivityControllerTest {

    @TestConfiguration
    static class MockConfig {
        @Bean
        ActivityService activityService() {
            return Mockito.mock(ActivityService.class);
        }
    }

    @Autowired MockMvc mockMvc;
    @Autowired ActivityService service;
    @Autowired ObjectMapper objectMapper;

    @Test
    void testGetAllActivities() throws Exception {
        Mockito.when(service.getAll())
                .thenReturn(List.of(new Activity("1", "Reading", "Desc", "Study", "2025-08-10", 60)));

        mockMvc.perform(get("/api/activities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Reading"));
    }

    @Test
    void testGetActivityById() throws Exception {
        Mockito.when(service.getById("1"))
                .thenReturn(Optional.of(new Activity("1", "Reading", "Desc", "Study", "2025-08-10", 60)));

        mockMvc.perform(get("/api/activities/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Reading"));
    }

    @Test
    void testCreateActivity() throws Exception {
        Activity activity = new Activity("1", "Reading", "Desc", "Study", "2025-08-10", 60);
        Mockito.when(service.create(any(Activity.class))).thenReturn(activity);

        mockMvc.perform(post("/api/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(activity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void testDeleteActivity() throws Exception {
        mockMvc.perform(delete("/api/activities/1"))
                .andExpect(status().isNoContent());
    }
}
