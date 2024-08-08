package com.aomerge.rentbooks;

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
@EnableMongoRepositories(basePackages = "com.aomerge.rentbooks.Repository")
@ComponentScan(basePackages = "com.aomerge.rentbooks")
public class RentBooksServiceApplication {

	public static void main(String[] args) {

		String activeProfile = System.getProperty("spring.profiles.active", "default");

		Dotenv dotenv;
		if ("production".equals(activeProfile)) {
			dotenv = Dotenv.configure().directory("./").load();
		} else {
			dotenv = Dotenv.configure().directory("./").load();
		}

		System.setProperty("HOST_EUREKA", dotenv.get("HOST_EUREKA"));
		System.setProperty("HOST_CONFIG", dotenv.get("HOST_CONFIG"));
		System.setProperty("PORT_CONFIG", dotenv.get("PORT_CONFIG"));
		System.setProperty("ADMIN_USER_CONFIG", dotenv.get("ADMIN_USER_CONFIG"));
		System.setProperty("ADMIN_PASSWORD_CONFIG", dotenv.get("ADMIN_PASSWORD_CONFIG"));
		System.setProperty("EUREKA_PORT", dotenv.get("EUREKA_PORT"));
		System.setProperty("RENT_BOOKS_PORT", dotenv.get("RENT_BOOKS_PORT"));
		System.setProperty("MONGODB_RENT_BOOKS", dotenv.get("MONGODB_RENT_BOOKS"));
		System.setProperty("JWT_CREATE_KEY", dotenv.get("JWT_CREATE_KEY"));

		System.out.println("HOST_EUREKA: " + System.getProperty("HOST_EUREKA"));

		SpringApplication.run(RentBooksServiceApplication.class, args);
	}

}
