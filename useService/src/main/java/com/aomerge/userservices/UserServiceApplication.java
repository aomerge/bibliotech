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

    protected static final Dotenv dotenv = Dotenv.configure().directory("./").load();

    public static void main(String[] args) {

        System.setProperty("HOST_CONFIG", dotenv.get("HOST_CONFIG"));
        System.setProperty("PORT_CONFIG", dotenv.get("PORT_CONFIG"));
        System.setProperty("ADMIN_USER_CONFIG", dotenv.get("ADMIN_USER_CONFIG"));
        System.setProperty("ADMIN_PASSWORD_CONFIG", dotenv.get("ADMIN_PASSWORD_CONFIG"));
        System.setProperty("EUREKA_PORT", dotenv.get("EUREKA_PORT"));
        System.setProperty("USER_PORT", dotenv.get("USER_PORT"));
        System.setProperty("MONGODB_USER", dotenv.get("MONGODB_USER"));
        System.setProperty("JWT_CREATE_KEY", dotenv.get("JWT_CREATE_KEY"));

        System.out.println("HOST_CONFIG: " + System.getProperty("HOST_CONFIG"));
        System.out.println("PORT_CONFIG: " + System.getProperty("PORT_CONFIG"));
        System.out.println("ADMIN_USER_CONFIG: " + System.getProperty("ADMIN_USER_CONFIG"));
        System.out.println("ADMIN_PASSWORD_CONFIG: " + System.getProperty("ADMIN_PASSWORD_CONFIG"));

        SpringApplication.run(UserServiceApplication.class, args);
    }
}
