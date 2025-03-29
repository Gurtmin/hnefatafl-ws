package com.example.hnefatafl.controller;

import com.example.hnefatafl.model.Game;
import com.example.hnefatafl.service.GameService;
import com.example.model.GameCreateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public Game startNewGame(@RequestBody GameCreateRequest request) {
        return gameService.startNewGame(request.getType());
    }
    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }
}
