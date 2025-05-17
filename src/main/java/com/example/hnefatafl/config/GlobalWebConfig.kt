package com.example.hnefatafl.config

import com.example.hnefatafl.log.BaseLoggable
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class GlobalWebConfig(private val requestInterceptor: RequestInterceptor) : BaseLoggable(), WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(requestInterceptor).addPathPatterns("/**")
        logger.info("✅ Applied interceptor RequestInterceptor to /**")
    }
}