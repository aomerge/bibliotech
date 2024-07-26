package com.aomerge.rentbooks.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Category {
    private static final String CATEGORY = "/api/v1/books-service";
    @GetMapping(CATEGORY + "/category")
    public ResponseEntity<?> GetCategory() {
        return ResponseEntity.ok("Category");
    }
}
