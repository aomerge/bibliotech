package com.aomerge.userservices.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/v1/user-service")
public class UserController {

    @GetMapping("/all")
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok("All users");
    }

}
