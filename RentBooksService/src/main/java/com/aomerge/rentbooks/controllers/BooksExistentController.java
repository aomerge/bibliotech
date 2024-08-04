package com.aomerge.rentbooks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/rent-books-service")
public class BooksExistentController {
    private static final String BOOKS = "/api/v1/rent-books-service";

    @GetMapping("/books")
    public String getAllBooks() {
        return "books";
    }


}
