package com.example.hnefatafl.config;

import com.example.hnefatafl.log.BaseLoggable;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("prod")
public class CorsProdConfig extends BaseLoggable implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://mojefrontendapp.cz")
                .allowedMethods("GET", "POST");
        logger.info("✅ PROD CORS aktivní – povolený origin: XXXX");

    }
}
