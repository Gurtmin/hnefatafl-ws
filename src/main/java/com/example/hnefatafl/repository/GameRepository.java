package com.example.hnefatafl.repository;

import com.example.hnefatafl.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {}
