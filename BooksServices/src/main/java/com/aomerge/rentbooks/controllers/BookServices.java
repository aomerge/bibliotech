package com.aomerge.rentbooks.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/books")
public class BookServices  {

    @GetMapping("/hello")
    public ResponseEntity<?> Hello() {
        return ResponseEntity.ok("Hello");
    }
}
