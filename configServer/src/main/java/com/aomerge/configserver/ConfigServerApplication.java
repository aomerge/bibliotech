package com.aomerge.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableConfigServer
//@EnableEurekaClient
public class ConfigServerApplication {
    protected static final Dotenv dotenv = Dotenv.configure().directory("../").load();
    protected static final Dotenv eurekaenv = Dotenv.configure().filename("eureka.env").directory("./config/env/").load();
    protected static final Dotenv gatewayenv = Dotenv.configure().filename("gateway.env").directory("../").load();
    protected static final Dotenv booksenv = Dotenv.configure().filename("books.env").directory("../").load();

    public static void main(String[] args) {
        // config properties the system in envorioment variables
        System.setProperty("HOST_CONFIG", dotenv.get("HOST_CONFIG"));
        System.setProperty("PORT_CONFIG", dotenv.get("PORT_CONFIG"));
        System.setProperty("PATH_CONFIG", dotenv.get("PATH_CONFIG"));
        System.setProperty("ADMIN_USER_CONFIG", dotenv.get("ADMIN_USER_CONFIG"));
        System.setProperty("ADMIN_PASSWORD_CONFIG", dotenv.get("ADMIN_PASSWORD_CONFIG"));

        // config propierties for eureka server
        System.setProperty("EUREKA_PORT", eurekaenv.get("EUREKA_PORT"));

        // config propierties for gateway server
        System.setProperty("GATEWAY_PORT", gatewayenv.get("GATEWAY_PORT"));

        // config propierties for books server
        System.setProperty("BOOKS_PORT", booksenv.get("BOOKS_PORT"));

        // run the application
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
