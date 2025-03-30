package com.example.hnefatafl.controller;

import com.example.api.model.GameChangeNameRequest;
import com.example.hnefatafl.model.MongoGame;
import com.example.hnefatafl.service.GameService;
import com.example.api.model.GameCreateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PatchMapping
    public Optional<MongoGame> renameGame(@RequestBody GameChangeNameRequest request) {
        return gameService.changeGameName(request.getId(), request.getType());
    }

    @PostMapping
    public MongoGame startNewGame(@RequestBody GameCreateRequest request) {
        return gameService.startNewGame(request.getType());
    }
    @GetMapping
    public List<MongoGame> getAllGames() {
        return gameService.getAllGames();
    }
}
