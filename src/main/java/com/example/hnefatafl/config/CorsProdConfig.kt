package com.example.hnefatafl.config

import com.example.hnefatafl.log.BaseLoggable
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@Profile("prod")
class CorsProdConfig : BaseLoggable(), WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "https://hnefatafl.cermak.info",
                "https://peti-hnefatafl.netlify.app"
            )
            .allowedMethods("GET", "POST")
        logger.info("✅ PROD CORS aktivní – povolený origin: XXXX")
    }
}