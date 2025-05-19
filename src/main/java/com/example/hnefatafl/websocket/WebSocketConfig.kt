package com.example.hnefatafl.websocket

import com.example.hnefatafl.log.BaseLoggable
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(
        private val gameSocketHandler: GameSocketHandler,
        @Value("\${spring.profiles.active:default}") private val profile: String
) : WebSocketConfigurer, BaseLoggable() {
        override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
                val allowedOrigins = when (profile) {
                        "prod" -> listOf(
                                "https://hnefatafl.cermak.info",
                                "https://peti-hnefatafl.netlify.app"
                        )
                        else -> listOf(
                                "http://localhost:*",
                                "https://*.netlify.app",
                                "https://*.cermak.info"
                        )
                }

                registry.addHandler(gameSocketHandler, "/ws/game")
                        .setAllowedOrigins(*allowedOrigins.toTypedArray())
                logger.info("WS CORS - Profile[{$profile}]: ${allowedOrigins.joinToString(", ")}")
        }
}