package com.aomerge.configserver;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
//@EnableEurekaClient
public class ConfigServerApplication {


    public static void main(String[] args) {
        String activeProfile = System.getProperty("spring.profiles.active", "default");

		Dotenv dotenv;
		if ("production".equals(activeProfile)) {
			dotenv = Dotenv.configure().directory("./").load();
		} else {
			dotenv = Dotenv.configure().directory("./").load();
		}
		System.setProperty("PATH_CONFIG", dotenv.get("PATH_CONFIG"));
		System.setProperty("HOST_CONFIG", dotenv.get("HOST_CONFIG"));
		System.setProperty("PORT_CONFIG", dotenv.get("PORT_CONFIG"));
		System.setProperty("ADMIN_USER_CONFIG", dotenv.get("ADMIN_USER_CONFIG"));
		System.setProperty("ADMIN_PASSWORD_CONFIG", dotenv.get("ADMIN_PASSWORD_CONFIG"));
        
        // run the application
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
