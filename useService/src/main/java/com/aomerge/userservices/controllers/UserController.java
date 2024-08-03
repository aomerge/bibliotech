package com.aomerge.userservices.controllers;

import com.aomerge.userservices.config.exeptions.CustomAuthorizationException;
import com.aomerge.userservices.config.validation.global.HeaderValidationDTO;
import com.aomerge.userservices.config.validation.user.BaseUserDTO;
import com.aomerge.userservices.models.User;
import com.aomerge.userservices.services.UserService;
import com.aomerge.userservices.services.serviceDTO.UserDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/user-service")
public class UserController {
    @Autowired
    private UserDTO userService;
    private static final String BOOKS = "/api/v1/user-service";

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(
            @RequestHeader(value = "Authorization", required = false) String token
    ) {
        try {
            // Valida el header de autorización
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(token);
            // Service
            return ResponseEntity.ok(userService.getAllUsers(headerValidationDTO));
        }catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable String id
    ) {
        try{
            // Valida el header de autorización
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(token);
            // Service
            return ResponseEntity.ok(userService.getUserById(headerValidationDTO,id));
        }catch (CustomAuthorizationException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> save(
            @RequestBody BaseUserDTO user
    ) {
        try {
            return ResponseEntity.ok(userService.save(user));
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @NotNull(message = "El email es requerido")
            @RequestParam String email,
            @NotNull(message = "La contraseña es requerida")
            @RequestParam String password
    ) {
        try {
            return ResponseEntity.ok(userService.login(email,password));
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PatchMapping("/users")
    public ResponseEntity<?> update(
            @RequestBody User user
    ) {
        try {
            return ResponseEntity.ok(userService.update(user));
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(
            @PathVariable String id
    ) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
