package com.aomerge.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    protected static final Dotenv dotenv = Dotenv.configure().directory("./").load();
    public static void main(String[] args) throws Exception {
        // config properties the system in envorioment variables
        System.setProperty("HOST_CONFIG", dotenv.get("HOST_CONFIG"));
        System.setProperty("PORT_CONFIG", dotenv.get("PORT_CONFIG"));
        System.setProperty("ADMIN_USER_CONFIG", dotenv.get("ADMIN_USER_CONFIG"));
        System.setProperty("ADMIN_PASSWORD_CONFIG", dotenv.get("ADMIN_PASSWORD_CONFIG"));
        // inicialing the application
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
