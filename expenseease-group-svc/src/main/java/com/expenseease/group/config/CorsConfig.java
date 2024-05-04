package com.expenseease.group.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Define the URL pattern to apply CORS configuration
                .allowedOrigins("*") // Specify allowed origins
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify allowed HTTP methods
                .allowedHeaders("*"); // Specify allowed headers
    }
}