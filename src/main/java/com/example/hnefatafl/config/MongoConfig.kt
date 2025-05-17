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

//package com.example.hnefatafl.config
//
//import com.mongodb.client.MongoClient
//import com.mongodb.client.MongoClients
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.mongodb.core.MongoTemplate
//import com.mongodb.client.MongoDatabase
//import jakarta.annotation.PostConstruct
//import org.bson.Document
//import org.slf4j.LoggerFactory
//
//@Configuration
//class MongoConfig(
//    @Value("\${spring.data.mongodb.uri}") private val uri: String,
//    @Value("\${spring.data.mongodb.database}") private val database: String
//) {
//
//    private val logger = LoggerFactory.getLogger(MongoConfig::class.java)
//
//    @Bean
//    fun mongoClient(): MongoClient {
//        return MongoClients.create(uri)
//    }
//
//    @Bean
//    fun mongoTemplate(mongoClient: MongoClient): MongoTemplate {
//        return MongoTemplate(mongoClient, database)
//    }
//
//    @PostConstruct
//    fun verifyMongoConnection() {
//        try {
//            val client = mongoClient()
//            val adminDb: MongoDatabase = client.getDatabase(database)
//            val result: Document = adminDb.runCommand(Document("ping", 1))
//            logger.warn("MONGO DB - Successfully connected", result.toJson())
//        } catch (e: Exception) {
//            logger.error("MONGO DB - Not connected", e)
//        }
//    }
//}
