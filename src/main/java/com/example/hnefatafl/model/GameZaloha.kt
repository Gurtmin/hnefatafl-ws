package com.example.hnefatafl.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class GameZaloha {
    @Id
    var id: String? = null
    var type: String? = null

    constructor()
    constructor(type: String?) {
        this.type = type
    }
}