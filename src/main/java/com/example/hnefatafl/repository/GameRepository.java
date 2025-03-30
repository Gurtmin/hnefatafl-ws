package com.example.hnefatafl.repository;

import com.example.hnefatafl.model.MongoGame;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<MongoGame, String> {}
