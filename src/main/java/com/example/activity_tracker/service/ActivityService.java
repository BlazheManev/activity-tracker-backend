package com.example.activity_tracker.service;

import com.example.activity_tracker.model.Activity;
import com.example.activity_tracker.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository repository;

    public List<Activity> getAll() {
        return repository.findAll();
    }

    public List<Activity> getByDate(String date) {
        return repository.findByDate(date);
    }

    public Optional<Activity> getById(String id) {
        return repository.findById(id);
    }

    public Activity create(Activity activity) {
        return repository.save(activity);
    }

    public Activity update(String id, Activity updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setDescription(updated.getDescription());
                    existing.setCategory(updated.getCategory());
                    existing.setDate(updated.getDate());
                    existing.setDurationMinutes(updated.getDurationMinutes());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Activity not found"));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
