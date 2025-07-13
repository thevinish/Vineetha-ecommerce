package com.vineetha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main Spring Boot Application class for Vineetha E-commerce Platform
 * Built by Vinish - Modern E-commerce Solution
 * 
 * @author Vinish
 * @version 1.0.0
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@EnableTransactionManagement
public class VineethaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VineethaApplication.class, args);
        System.out.println("🚀 Vineetha E-commerce Platform Started Successfully!");
        System.out.println("📱 Built by Vinish");
        System.out.println("🌐 API Documentation: http://localhost:8080/api/v1/swagger-ui.html");
        System.out.println("🔗 Health Check: http://localhost:8080/api/v1/health");
    }
} 