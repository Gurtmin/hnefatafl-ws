package com.example.hnefatafl.model;

import org.springframework.data.annotation.Id;
import com.example.mongo.model.Game;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MongoGame extends Game {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}