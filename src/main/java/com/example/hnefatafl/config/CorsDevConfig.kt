package com.example.hnefatafl.config

import com.example.hnefatafl.log.BaseLoggable
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@Profile("dev")
class CorsDevConfig : BaseLoggable(), WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/**")
//            .allowedOrigins("*")
//            .allowedMethods("GET", "POST", "PUT", "DELETE")
//        logger.info("✅ DEV CORS aktivní – povolený origin: *")
    }
}

// TODO toto odmazat a CorsProdConfig zmenit na CorsConfig