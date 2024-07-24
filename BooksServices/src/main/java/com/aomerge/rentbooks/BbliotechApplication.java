package com.aomerge.rentbooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BbliotechApplication {
	protected static final Dotenv dotenv = Dotenv.configure().directory("../").load();

	public static void main(String[] args) {
		// config properties the system in envorioment variables
		System.setProperty("HOST_CONFIG", dotenv.get("HOST_CONFIG"));
		System.setProperty("PORT_CONFIG", dotenv.get("PORT_CONFIG"));
		System.setProperty("ADMIN_USER_CONFIG", dotenv.get("ADMIN_USER_CONFIG"));
		System.setProperty("ADMIN_PASSWORD_CONFIG", dotenv.get("ADMIN_PASSWORD_CONFIG"));

		SpringApplication.run(BbliotechApplication.class, args);
	}

}
