package com.example.hnefatafl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.hnefatafl.repository")
public class MongoRepoConfig {
}
