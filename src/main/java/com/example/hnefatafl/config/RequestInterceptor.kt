package com.example.hnefatafl.config

import com.example.hnefatafl.context.RequestContext.clear
import com.example.hnefatafl.context.RequestContext.clientId
import com.example.hnefatafl.context.RequestContext.correlationId
import com.example.hnefatafl.log.BaseLoggable
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.io.BufferedReader
import java.util.stream.Collectors


@Component
class RequestInterceptor : BaseLoggable(), HandlerInterceptor {
//    private val logger = LoggerFactory.getLogger(RequestInterceptor::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val headerValue = request.getHeader("X-Client-ID")
        correlationId = request.getHeader("X-Correlation-ID") ?: "UNDEFINED"

        return if (headerValue == null) {
            val reader: BufferedReader = request.reader
            val body: String = reader.lines().collect(Collectors.joining(System.lineSeparator()))
            logger.error("REQUEST: ${request.method} ${request.requestURI} - Missing header 'X-Client-ID'")
            logger.warn("REQUEST: ${request.method} ${request.requestURI} - BODY: ${body}")
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Chybí hlavička X-Client-ID")
            false
        } else {
            clientId = headerValue
            true
        }
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        clear() // důležité pro thread safety
    }
}