package com.aomerge.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
//@EnableEurekaClient
public class ConfigServerApplication {


    public static void main(String[] args) {
        // run the application
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
