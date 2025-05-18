package com.example.hnefatafl.websocket

import com.example.hnefatafl.log.BaseLoggable
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class GameSocketHandler : TextWebSocketHandler() {
    private val logger: Logger = LoggerFactory.getLogger(GameSocketHandler::class.java)
    private val sessions = mutableSetOf<WebSocketSession>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
        logger.warn("WEBSOCKET - connected session: ${session.id}")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        logger.warn("WEBSOCKET - message received: ${message.payload}")
        // Příklad: rozeslání zprávy všem
        sessions.forEach {
            it.sendMessage(TextMessage("Zpráva: ${message.payload}"))
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: org.springframework.web.socket.CloseStatus) {
        sessions.remove(session)
        logger.warn("WEBSOCKET - disconnected session: ${session.id}")
    }
}
