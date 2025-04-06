package com.example.hnefatafl.repository;

import com.example.hnefatafl.model.MongoGame;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameRepository extends MongoRepository<MongoGame, String> {
    List<MongoGame> findByType(String type);
}
