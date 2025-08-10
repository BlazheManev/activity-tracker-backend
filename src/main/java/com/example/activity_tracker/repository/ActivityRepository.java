package com.example.activity_tracker.repository;
import com.example.activity_tracker.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> findByDate(String date);
}
