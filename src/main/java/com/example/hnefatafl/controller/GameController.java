package com.example.hnefatafl.controller;

import com.example.api.model.Game;
import com.example.api.model.GameChangeNameRequest;
import com.example.api.model.PagedGameResponse;
import com.example.hnefatafl.model.MongoGame;
import com.example.hnefatafl.service.GameService;
import com.example.api.model.GameCreateRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<MongoGame> renameGame(@RequestBody GameChangeNameRequest request) {
        MongoGame updated = gameService.changeGameName(request.getId(), request.getType());
        return ResponseEntity.ok( updated);
    }

    @PostMapping
    public MongoGame createNewGame(@RequestBody GameCreateRequest request) {
        return gameService.createNewGame(request.getType());
    }
    @GetMapping
    public ResponseEntity<PagedGameResponse> getAllGames(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        PagedGameResponse response = gameService.getAllGames(PageRequest.of(page, size));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable String id) {
        Game game = gameService.getGameById(String.valueOf(id));
        return ResponseEntity.ok(game);
    }
}
