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

        // inicialing the application
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
