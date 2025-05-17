package com.example.hnefatafl.controller

import com.example.hnefatafl.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/status")
class StatusController(private val gameService: GameService) {
    @Autowired
    private val env: Environment? = null

    @get:GetMapping
    val status: ResponseEntity<Map<String, String>>
        get() {
            val envMap: MutableMap<String, String> = HashMap()
            envMap["profile"] =
                if ((env?.activeProfiles?.size ?: 0) > 0)
                    java.lang.String.join(",", *env?.activeProfiles)
                else "default"
            envMap["service"] = gameService.javaClass.simpleName
            envMap["spring.application.name"] = env?.getProperty("spring.application.name") ?: "not defined"
            envMap["server.port"] = env?.getProperty("server.port") ?: "not defined"
            envMap["spring.data.mongodb.uri"] = env?.getProperty("spring.data.mongodb.uri") ?: "not defined"
            envMap["spring.data.mongodb.database"] = env?.getProperty("spring.data.mongodb.database") ?: "not defined"
            return ResponseEntity.ok(envMap)
        }
}