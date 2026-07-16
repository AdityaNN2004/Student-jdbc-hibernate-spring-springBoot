package com.studentSpringBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfig {

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply to all API paths across your application
                        .allowedOrigins("http://localhost:5173") // Allow your React development port
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Authorize all standard operations
                        .allowedHeaders("*") // Accept all incoming headers from the frontend
                        
                        // 💡 THE CRITICAL LINE: Explicitly gives permission to React to extract the secure JWT token string!
                        .exposedHeaders("Authorization") 
                        
                        .allowCredentials(true); // Allow sending browser session context if needed
            }
        };
    }
}
