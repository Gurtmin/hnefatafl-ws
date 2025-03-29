package com.example.hnefatafl.controller;

import com.example.hnefatafl.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final GameService gameService;

    public StatusController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> getStatus() {
        String serviceType = gameService.getClass().getSimpleName();
        return ResponseEntity.ok(Map.of("service", serviceType));
    }
}
