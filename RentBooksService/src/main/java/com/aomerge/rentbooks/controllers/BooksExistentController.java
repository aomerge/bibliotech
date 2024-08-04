package com.aomerge.rentbooks.controllers;

import com.aomerge.rentbooks.config.validation.baseBookExisten.BaseBookInExistenDTO;
import com.aomerge.rentbooks.config.validation.global.HeaderValidationDTO;
import com.aomerge.rentbooks.services.DTO.BookExistentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/rent-books-service")
public class BooksExistentController {
    @Autowired
    private BookExistentDTO bookExistentService;

    private static final String BOOKS = "/api/v1/rent-books-service";

    @GetMapping("/book/{id}")
    public ResponseEntity<?> findById(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable String id
    ) {
        try {
            // Validation Header
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(token);
            return ResponseEntity.ok(bookExistentService.findById(headerValidationDTO,id));
        }catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<?> findByBookId(
            @RequestHeader(value ="Authorization", required = false) String token,
            @PathVariable String bookId
    ) {
        try {
            // Validation Header
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(token);
            return ResponseEntity.ok(bookExistentService.findByBookId(headerValidationDTO,bookId));
        }catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/book")
    public ResponseEntity<?> save(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestBody BaseBookInExistenDTO booksInExisten
    ) {
        try {
            // Validation Header
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(token);
            // Service
            return ResponseEntity.ok(bookExistentService.save(headerValidationDTO,booksInExisten));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PatchMapping("/book")
    public ResponseEntity<?> update(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestBody BaseBookInExistenDTO booksInExisten
    ) {
        try {
            // Validation Header
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(token);
            // Service
            return ResponseEntity.ok(bookExistentService.update(headerValidationDTO,booksInExisten));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PatchMapping("/book/{id}")
    public ResponseEntity<?> updateAddQuantity(
            @RequestHeader(value="Authorization", required = false) String token,
            @PathVariable String id,
            @RequestParam int quantity
    ) {
        try {
            // Validation Header
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(token);
            // Service
            bookExistentService.updateAddQuantity(headerValidationDTO,id,quantity);
            return ResponseEntity.ok().body("Book added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> delete(
            @RequestHeader(value="Authorization", required = false) String token,
            @PathVariable String id
    ) {
        try {
            // Validation Header
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(token);
            // Service
            bookExistentService.delete(headerValidationDTO,id);
            return ResponseEntity.ok().body("Book deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

}
