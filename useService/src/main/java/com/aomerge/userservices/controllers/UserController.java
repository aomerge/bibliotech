package com.aomerge.userservices.controllers;

import com.aomerge.userservices.config.validation.global.HeaderValidationDTO;
import com.aomerge.userservices.models.User;
import com.aomerge.userservices.services.UserService;
import com.aomerge.userservices.services.serviceDTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/api/v1/user-service")
public class UserController {
    @Autowired
    private UserDTO userService;
    private static final String BOOKS = "/api/v1/user-service";

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(
            @RequestHeader(value = "Authorization", required = false) String token
    ) {
        try {
            // Valida el header de autorización
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(token);
            // Service
            return ResponseEntity.ok(userService.getAllUsers(headerValidationDTO));
        }catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

}
