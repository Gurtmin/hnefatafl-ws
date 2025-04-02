package com.example.hnefatafl.controller;

import com.example.hnefatafl.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final GameService gameService;

    public StatusController(GameService gameService) {
        this.gameService = gameService;
    }

    @Autowired
    private Environment env;

    @GetMapping
    public ResponseEntity<Map<String, String>> getStatus() {
        Map<String, String> envMap = new HashMap<>();

        envMap.put("profile", env.getActiveProfiles().length>0?String.join(",",env.getActiveProfiles()):"default");
        envMap.put("service", gameService.getClass().getSimpleName());
        envMap.put( "spring.application.name", env.getProperty("spring.application.name"));
        envMap.put( "server.port", env.getProperty("server.port"));
        envMap.put( "spring.data.mongodb.uri", env.getProperty("spring.data.mongodb.uri"));
        envMap.put( "spring.data.mongodb.database", env.getProperty("spring.data.mongodb.database"));

        return ResponseEntity.ok(envMap);
    }
}
