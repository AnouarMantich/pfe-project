package org.app.consultationservice.controller;

import lombok.RequiredArgsConstructor;
import org.app.consultationservice.health.DatabaseHealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/health")
@RequiredArgsConstructor
public class HealthController {

    private final DatabaseHealthIndicator databaseHealthIndicator;

    @GetMapping
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("service", "consultation-service");
        health.put("database", databaseHealthIndicator.getDatabaseStatus());
        health.put("timestamp", System.currentTimeMillis());
        
        return ResponseEntity.ok(health);
    }

    @GetMapping("/database")
    public ResponseEntity<Map<String, Object>> databaseHealth() {
        Map<String, Object> health = new HashMap<>();
        boolean isHealthy = databaseHealthIndicator.isDatabaseHealthy();
        health.put("status", isHealthy ? "UP" : "DOWN");
        health.put("database", "PostgreSQL");
        health.put("message", databaseHealthIndicator.getDatabaseStatus());
        health.put("timestamp", System.currentTimeMillis());
        
        return ResponseEntity.ok(health);
    }
}
