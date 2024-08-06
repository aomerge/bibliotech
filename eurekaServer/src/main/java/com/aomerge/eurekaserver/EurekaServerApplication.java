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
        System.setProperty("EUREKA_PORT", dotenv.get("EUREKA_PORT"));
        System.setProperty("CONFIG_URL", dotenv.get("CONFIG_URL"));
        System.setProperty("ADMIN_USER_CONFIG", dotenv.get("ADMIN_USER_CONFIG"));
        System.setProperty("ADMIN_PASSWORD_CONFIG", dotenv.get("ADMIN_PASSWORD_CONFIG"));

        System.out.println("EUREKA_PORT: " + System.getProperty("EUREKA_PORT"));
        System.out.println("CONFIG_URI: " + System.getProperty("CONFIG_URL"));
        System.out.println("ADMIN_USER_CONFIG: " + System.getProperty("ADMIN_USER_CONFIG"));
        System.out.println("ADMIN_PASSWORD_CONFIG: " + System.getProperty("ADMIN_PASSWORD_CONFIG"));

        // inicialing the application
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
