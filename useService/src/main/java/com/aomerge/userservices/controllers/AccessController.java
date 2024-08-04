package com.aomerge.userservices.controllers;

import com.aomerge.userservices.config.validation.access.BaseAccessDTO;
import com.aomerge.userservices.config.validation.global.HeaderValidationDTO;
import com.aomerge.userservices.repository.AccessRepository;
import com.aomerge.userservices.services.serviceDTO.AccessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/user-service")
public class AccessController {
    @Autowired
    private AccessDTO accessService;
    private static final String ACCESS = "/api/v1/user-service/access";

    @PostMapping("/access")
    public ResponseEntity<?> save(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestBody BaseAccessDTO access
    ) {
        try {
            // Valida el header de autorización
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(token);
            // Service
            return ResponseEntity.ok(accessService.save(headerValidationDTO, access));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PatchMapping("/access")
    public ResponseEntity<?> update(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestBody BaseAccessDTO access
    ) {
        try {
            // Valida el header de autorización
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(token);
            // Service
            return ResponseEntity.ok(accessService.save(headerValidationDTO, access));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
    @DeleteMapping("/access")
    public ResponseEntity<?> delete(
            @RequestHeader(value="Authorization", required = false) String token,
            @RequestBody BaseAccessDTO access
    ) {
        try {
            // Valida el header de autorización
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(token);
            // Service
            return ResponseEntity.ok(accessService.save(headerValidationDTO, access));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
