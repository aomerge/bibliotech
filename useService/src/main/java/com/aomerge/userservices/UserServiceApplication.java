package com.aomerge.userservices;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.aomerge.userservices.repository")
@ComponentScan(basePackages = "com.aomerge.userservices")
public class UserServiceApplication {

    public static void main(String[] args) {

        String activeProfile = System.getProperty("spring.profiles.active", "default");

        Dotenv dotenv;
        if ("production".equals(activeProfile)) {
            dotenv = Dotenv.configure().directory("./").load();
        } else {
            dotenv = Dotenv.configure().directory("../").load();
        }

        System.setProperty("HOST_EUREKA", dotenv.get("HOST_EUREKA"));
        System.setProperty("HOST_CONFIG", dotenv.get("HOST_CONFIG"));
        System.setProperty("PORT_CONFIG", dotenv.get("PORT_CONFIG"));
        System.setProperty("ADMIN_USER_CONFIG", dotenv.get("ADMIN_USER_CONFIG"));
        System.setProperty("ADMIN_PASSWORD_CONFIG", dotenv.get("ADMIN_PASSWORD_CONFIG"));
        System.setProperty("EUREKA_PORT", dotenv.get("EUREKA_PORT"));
        System.setProperty("USER_PORT", dotenv.get("USER_PORT"));
        System.setProperty("MONGODB_USER", dotenv.get("MONGODB_USER"));
        System.setProperty("JWT_CREATE_KEY", dotenv.get("JWT_CREATE_KEY"));

        SpringApplication.run(UserServiceApplication.class, args);
    }
}
