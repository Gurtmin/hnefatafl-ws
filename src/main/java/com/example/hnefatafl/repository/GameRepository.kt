package com.example.hnefatafl.repository

import com.example.hnefatafl.model.MongoGame
import org.springframework.data.mongodb.repository.MongoRepository

interface GameRepository : MongoRepository<MongoGame?, String?> {
    fun findByType(type: String?): List<MongoGame?>?
}