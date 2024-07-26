package com.aomerge.rentbooks.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchOffice {
    private static final String BRANCH_OFFICE = "/api/v1/books-service";
    @GetMapping(BRANCH_OFFICE + "/BranchOffice")
    public ResponseEntity<?> GetBranchOffice() {
        return ResponseEntity.ok("BranchOffice");
    }
}
