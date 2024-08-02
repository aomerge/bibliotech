package com.aomerge.rentbooks;

import com.aomerge.rentbooks.services.BooksService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.aomerge.rentbooks.repository")
@ComponentScan(basePackages = "com.aomerge.rentbooks")
public class BbliotechApplication {
	protected static final Dotenv dotenv = Dotenv.configure().directory("../").load();

	public static void main(String[] args) {

		// config properties the system in envorioment variables
		System.setProperty("HOST_CONFIG", dotenv.get("HOST_CONFIG"));
		System.setProperty("PORT_CONFIG", dotenv.get("PORT_CONFIG"));
		System.setProperty("ADMIN_USER_CONFIG", dotenv.get("ADMIN_USER_CONFIG"));
		System.setProperty("ADMIN_PASSWORD_CONFIG", dotenv.get("ADMIN_PASSWORD_CONFIG"));
		System.setProperty("EUREKA_PORT", dotenv.get("EUREKA_PORT"));
		System.setProperty("BOOKS_PORT", dotenv.get("BOOKS_PORT"));
		System.setProperty("MONGODB_BOOKS", dotenv.get("MONGODB_BOOKS"));
		System.setProperty("JWT_CREATE_KEY", dotenv.get("JWT_CREATE_KEY"));

		SpringApplication.run(BbliotechApplication.class, args);
	}

}
