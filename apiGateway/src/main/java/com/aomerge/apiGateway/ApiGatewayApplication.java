package com.aomerge.apiGateway;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.env.EnvironmentPostProcessor;

import static com.netflix.config.DeploymentContext.ContextKey.environment;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    protected static final Dotenv dotenv = Dotenv.configure().directory("./").load();

    public static void main(String[] args) {



        // config properties the system in envorioment variables
        System.setProperty("HOST_CONFIG", dotenv.get("HOST_CONFIG"));
        System.setProperty("PORT_CONFIG", dotenv.get("PORT_CONFIG"));
        System.setProperty("ADMIN_USER_CONFIG", dotenv.get("ADMIN_USER_CONFIG"));
        System.setProperty("ADMIN_PASSWORD_CONFIG", dotenv.get("ADMIN_PASSWORD_CONFIG"));
        System.setProperty("EUREKA_PORT", dotenv.get("EUREKA_PORT"));
        System.setProperty("GATEWAY_PORT", dotenv.get("GATEWAY_PORT"));
        System.setProperty("BOOKS_PORT", dotenv.get("BOOKS_PORT"));

        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
