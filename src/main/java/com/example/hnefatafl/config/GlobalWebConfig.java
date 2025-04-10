package com.example.hnefatafl.config;

import com.example.hnefatafl.log.BaseLoggable;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalWebConfig extends BaseLoggable implements WebMvcConfigurer {
    private final RequestInterceptor requestInterceptor;

    public GlobalWebConfig(RequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor).addPathPatterns("/**");
        logger.info("✅ Applied interceptor RequestInterceptor to /**");
    }
}
