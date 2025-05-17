package com.example.hnefatafl.config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongoConfig {
    @Bean
    fun mongoClient(@Value("\${spring.data.mongodb.uri}") uri: String): MongoClient {
        return MongoClients.create(uri)
    }

    @Bean
    fun mongoTemplate(
        mongoClient: MongoClient,
        @Value("\${spring.data.mongodb.database}") database: String
    ): MongoTemplate {
        return MongoTemplate(mongoClient, database)
    }
}