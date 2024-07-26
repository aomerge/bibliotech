package com.aomerge.configserver.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/** This class is used to load custom environment properties from dotenv files */
public class CustomEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("Loading custom environment properties...");

        // Load dotenv files
        Dotenv dotenv = Dotenv.configure().directory("../").load();

        Map<String, Object> propertySource = new HashMap<>();

        // Configurar propiedades del sistema desde dotenv
        propertySource.put("HOST_CONFIG", dotenv.get("HOST_CONFIG"));
        propertySource.put("PORT_CONFIG", dotenv.get("PORT_CONFIG"));
        propertySource.put("PATH_CONFIG", dotenv.get("PATH_CONFIG"));
        propertySource.put("ADMIN_USER_CONFIG", dotenv.get("ADMIN_USER_CONFIG"));
        propertySource.put("ADMIN_PASSWORD_CONFIG", dotenv.get("ADMIN_PASSWORD_CONFIG"));

        // create eviroment properties
        environment.getPropertySources().addFirst(new MapPropertySource("customProperties", propertySource));
    }
}
