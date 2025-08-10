package com.example.activity_tracker.controller;

import com.example.activity_tracker.model.Activity;
import com.example.activity_tracker.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow all for frontend dev
public class ActivityController {

    private final ActivityService service;

    @GetMapping
    public List<Activity> getAll(@RequestParam(required = false) String date) {
        if (date != null) {
            return service.getByDate(date);
        }
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Activity create(@RequestBody Activity activity) {
        return service.create(activity);
    }

    @PutMapping("/{id}")
    public Activity update(@PathVariable String id, @RequestBody Activity activity) {
        return service.update(id, activity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
