package com.example.hnefatafl.service;

import com.example.hnefatafl.model.Game;
import com.example.hnefatafl.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game startNewGame(String type) {
        return gameRepository.save(new Game(type));
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}
