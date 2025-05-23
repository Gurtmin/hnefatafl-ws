package com.example.hnefatafl.config

import com.example.hnefatafl.log.BaseLoggable
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsProdConfig(
    @Value("\${spring.profiles.active:default}") private val profile: String
) : BaseLoggable(){
    @Bean
    fun corsFilter(): FilterRegistrationBean<CorsFilter> {
        val source = UrlBasedCorsConfigurationSource()
        val config = when (profile) {
            "prod" ->
                CorsConfiguration().apply {
                    allowedOrigins = listOf(
                        "https://hnefatafl.cermak.info",
                        "https://peti-hnefatafl.netlify.app"
                    )
                    allowedMethods = listOf("GET", "POST", "OPTIONS")
                    allowedHeaders = listOf("*")
                    allowCredentials = true
                }
            else ->
                CorsConfiguration().apply {
                    allowedOrigins = listOf(
                        "http://localhost:5173",
                        "https://hnefatafl.cermak.info",
                        "https://peti-hnefatafl.netlify.app"
                    )
                    allowedMethods = listOf("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH")
                    allowedHeaders = listOf("*")
                    allowCredentials = true
                }
        }

        source.registerCorsConfiguration("/**", config)

        logger.warn("CORS – Profile[{$profile}]: ${config.allowedOrigins?.joinToString(", ")?:"None !!!"}")

        return FilterRegistrationBean(CorsFilter(source)).apply {
            order = Ordered.HIGHEST_PRECEDENCE // zajistí, že se aplikuje jako první
        }
    }
}



//@Configuration
//@Profile("prod")
//class CorsProdConfig : BaseLoggable(), WebMvcConfigurer {
//    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/**")
//            .allowedOrigins(
//                "https://hnefatafl.cermak.info",
//                "https://peti-hnefatafl.netlify.app"
//            )
//            .allowedMethods("GET", "POST", "OPTIONS")
//            .allowedHeaders("*")
//            .allowCredentials(true)
//        logger.warn("✅ PROD CORS aktivní – povolený origin: XXXX")
//    }
//}