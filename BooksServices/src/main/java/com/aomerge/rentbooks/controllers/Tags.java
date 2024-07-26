package com.aomerge.rentbooks.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Tags {
    private static final String TAGS = "/api/v1/books-service";
    @GetMapping(TAGS + "/Tags")
    public ResponseEntity<?> GetTags() {
        return ResponseEntity.ok("Tags");
    }
}
