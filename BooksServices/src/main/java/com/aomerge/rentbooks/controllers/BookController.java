package com.aomerge.rentbooks.controllers;

import com.aomerge.rentbooks.models.Book;
import com.aomerge.rentbooks.services.BooksService;
import com.aomerge.rentbooks.services.DTO.BooksDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/books-service")
public class BookController {
    @Autowired
    private BooksDTO booksService;
    private static final String BOOKS = "/api/v1/books-service";

    @Operation(summary = "Hello", description = "Hello")
    @ApiResponse(responseCode = "200", description = "Hello")
    @GetMapping("/api/v1/books-service/hello")
    public ResponseEntity<?> Hello() {
        return ResponseEntity.ok("Hello");
    }

    @Operation(summary = "Get all books", description = "Get all books")
    @ApiResponse(responseCode = "200", description = "Get all books")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @GetMapping("/books")
    public ResponseEntity<?> getBooks() {
        return ResponseEntity.ok(booksService.TestCreateBook());
    }

    @GetMapping("/books/search")
    public ResponseEntity<?> searchBooks(@RequestParam String title) {
        return ResponseEntity.ok(booksService.TestCreateBook());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable String id) {
        return ResponseEntity.ok(booksService.TestCreateBook());
    }

    @PostMapping( "/book/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(booksService.createSampleBook());
    }

    @PatchMapping("/book/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        return ResponseEntity.ok(booksService.createSampleBook());
    }

    @DeleteMapping("/book/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        return ResponseEntity.ok("Book deleted");
    }


}
