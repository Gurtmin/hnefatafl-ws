package com.example.hnefatafl.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.ConnectionString;
import com.mongodb.MongoTimeoutException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class MongoConfig {

    private static final Logger log = LoggerFactory.getLogger(MongoConfig.class);

    @Bean
    public MongoClient mongoClient(@Value("${spring.data.mongodb.uri}") String uri) {
        try {
            MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .applyToSocketSettings(builder ->
                    builder.connectTimeout(3_000, java.util.concurrent.TimeUnit.MILLISECONDS))
                .build();
            return MongoClients.create(settings);
        } catch (MongoTimeoutException e) {
            log.warn("⚠️ MongoDB connection timed out: {}", e.getMessage());
            return null;
        }
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient,
                                       @Value("${spring.data.mongodb.database}") String database) {
        return new MongoTemplate(mongoClient, database);
    }
}
