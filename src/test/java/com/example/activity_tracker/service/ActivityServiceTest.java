package com.example.activity_tracker.service;

import com.example.activity_tracker.model.Activity;
import com.example.activity_tracker.repository.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActivityServiceTest {

    private ActivityRepository repository;
    private ActivityService service;

    @BeforeEach
    void setUp() {
        repository = mock(ActivityRepository.class);
        service = new ActivityService(repository);
    }

    @Test
    void testGetAllActivities() {
        when(repository.findAll()).thenReturn(List.of(new Activity("1", "Reading", "Desc", "Study", "2025-08-10", 60)));
        List<Activity> activities = service.getAll();
        assertEquals(1, activities.size());
        assertEquals("Reading", activities.get(0).getName());
    }

    @Test
    void testGetActivityById() {
        Activity activity = new Activity("1", "Reading", "Desc", "Study", "2025-08-10", 60);
        when(repository.findById("1")).thenReturn(Optional.of(activity));
        Optional<Activity> found = service.getById("1");
        assertTrue(found.isPresent());
        assertEquals("Reading", found.get().getName());
    }

    @Test
    void testCreateActivity() {
        Activity activity = new Activity(null, "Reading", "Desc", "Study", "2025-08-10", 60);
        Activity saved = new Activity("1", "Reading", "Desc", "Study", "2025-08-10", 60);
        when(repository.save(ArgumentMatchers.any(Activity.class))).thenReturn(saved);
        Activity result = service.create(activity);
        assertNotNull(result.getId());
    }

    @Test
    void testDeleteActivity() {
        service.delete("1");
        verify(repository, times(1)).deleteById("1");
    }
}
