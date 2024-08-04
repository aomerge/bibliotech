package com.aomerge.rentbooks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BooksExistent {

        @GetMapping
        public String booksExistent() {
            return "booksExistent";
        }
}
