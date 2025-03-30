package com.example.hnefatafl.service;

import com.example.hnefatafl.model.MongoGame;
import com.example.hnefatafl.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public MongoGame startNewGame(String type) {
        MongoGame mongoGame = new MongoGame();
        mongoGame.setType(type);
        return gameRepository.save(mongoGame);
    }

    public List<MongoGame> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<MongoGame> changeGameName(String id, String type) {
        return gameRepository.findById( id);
    }
}
