package com.aomerge.rentbooks.controllers;

import com.aomerge.rentbooks.config.exeptions.UserBadRequest;
import com.aomerge.rentbooks.config.exeptions.UserNotExistException;
import com.aomerge.rentbooks.config.validation.books.BaseBookDTO;
import com.aomerge.rentbooks.config.validation.books.IdBookvalidationDTO;
import com.aomerge.rentbooks.config.validation.groups.OnCreate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdateAll;
import com.aomerge.rentbooks.models.Book;
import com.aomerge.rentbooks.services.DTO.BooksDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        try {
            return ResponseEntity.ok(booksService.getAllBooks());
        }catch (ExceptionInInitializerError e) {
            return ResponseEntity.status(404).body("Internal Server Error");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @Operation(summary = "Search books", description = "Search books")
    @ApiResponse(responseCode = "200", description = "Get all books")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/books/search")
    public ResponseEntity<?> searchBooks(@RequestParam String item) {
        try {
            List<Book> Response = booksService.searchBooks(item);
            if (Response.isEmpty()) {
                throw new ExceptionInInitializerError("No books found");
            }
            return ResponseEntity.ok(Response);
        }catch (ExceptionInInitializerError e) {
            return ResponseEntity.status(404).body("404 Not Found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @Operation(summary = "Get a book", description = "Get a book")
    @ApiResponse(responseCode = "200", description = "Get a book")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable String id) {
        try {
            Book book = booksService.getBook(id);
            if (book == null) {
                throw new ExceptionInInitializerError("Book not found");
            }
            return ResponseEntity.ok(book);
        } catch (ExceptionInInitializerError e) {
            return ResponseEntity.status(404).body("{data: 404 Not Found}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @Operation(summary = "Create a book", description = "Create a book")
    @ApiResponse(responseCode = "200", description = "Book created")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PostMapping( "/book/create")
    public ResponseEntity<?> createBook(@Validated(OnCreate.class) @RequestBody BaseBookDTO bookDTO, BindingResult result) {
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        try{
            return ResponseEntity.ok(booksService.createBook(bookDTO));
        }catch (ExceptionInInitializerError e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @Operation(summary = "Update a book", description = "Update a book")
    @ApiResponse(responseCode = "200", description = "Book updated")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PutMapping("/book/update/{id}")
    public ResponseEntity<?> updateBook(@Validated(OnUpdateAll.class) @PathVariable String id, @RequestBody BaseBookDTO book, BindingResult result) {
        try {
            return ResponseEntity.ok(booksService.updateBook(id, book));
        } catch (UserBadRequest e){
            return ResponseEntity.status(400).body(e.getMessage());
        }catch (UserNotExistException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error"+ e.getMessage());
        }
    }

    @Operation(summary = "Update a book", description = "Update a book")
    @ApiResponse(responseCode = "200", description = "Book updated")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PatchMapping("/book/update")
    public ResponseEntity<?> updateBook(@Validated(OnUpdate.class) @RequestBody BaseBookDTO book , BindingResult result) {
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->{
                        errors.put("code", "400" );
                        errors.put("messenger", error.getDefaultMessage());
                        errors.put("date", LocalDateTime.now().toString());
                    });
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            return ResponseEntity.ok(booksService.updateBook(book));
        } catch (UserNotExistException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error"+ e.getMessage());
        }
    }

    @Operation(summary = "Delete a book", description = "Delete a book")
    @ApiResponse(responseCode = "200", description = "Book deleted")
    @DeleteMapping("/book/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        try {
            JSONObject response = new JSONObject();
            response.put("code", 200);
            response.put("message", booksService.deleteBook(id));
            response.put("date", LocalDateTime.now().toString());
            return ResponseEntity.ok(response.toString());
        } catch (UserNotExistException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }


}
