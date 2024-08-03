package com.aomerge.rentbooks.controllers;

import com.aomerge.rentbooks.config.exeptions.CustomAuthorizationException;
import com.aomerge.rentbooks.config.exeptions.UserBadRequest;
import com.aomerge.rentbooks.config.exeptions.UserNotExistException;
import com.aomerge.rentbooks.config.validation.category.BaseCaterogyDTO;
import com.aomerge.rentbooks.config.validation.global.HeaderValidationDTO;
import com.aomerge.rentbooks.config.validation.groups.OnCreate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdate;
import com.aomerge.rentbooks.services.DTO.CategoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
    @Autowired
    private CategoryDTO categoryService;
    private static final String CATEGORY = "/api/v1/books-service";
    @Operation(summary = "Get all categories")
    @ApiResponse(responseCode = "200", description = "Return all categories")
    @ApiResponse(responseCode = "400", description = "Error in the request")
    @GetMapping(CATEGORY + "/categories")
    public ResponseEntity<?> GetCategory() {
        try{
            return ResponseEntity.ok(categoryService.getAllCategory());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Get a category")
    @ApiResponse(responseCode = "200", description = "Return a category")
    @ApiResponse(responseCode = "400", description = "Error in the request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping(CATEGORY + "/category/{id}")
    public ResponseEntity<?> GetCategory(
           @PathVariable String id
    ) {
        try{
            System.out.println("id: " + id);
            return ResponseEntity.ok(categoryService.getCategory(id));
        }catch (UserNotExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all books from a category")
    @ApiResponse(responseCode = "200", description = "Return all books from a category")
    @ApiResponse(responseCode = "400", description = "Error in the request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping(CATEGORY + "/category/{id}/books")
    public ResponseEntity<?> GetCategoryBooks(
           @PathVariable String id
    ) {
        try{
            return ResponseEntity.ok(categoryService.getCategoryBooks(id));
        }
        catch (UserBadRequest e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @Operation(summary = "Add a book to a category")
    @ApiResponse(responseCode = "200", description = "Book added to category successfully")
    @ApiResponse(responseCode = "400", description = "Error in the request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PatchMapping(CATEGORY + "/category/add/books")
    public ResponseEntity<?> PutCategoryBooks(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader,
            @RequestBody BaseCaterogyDTO category
    ) {
        try{
            // Valida el header de autorización
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(authorizationHeader);
            // service
            categoryService.putBook(headerValidationDTO, category);
            return ResponseEntity.ok("Book added to category successfully");
        }catch (UserNotExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Create a category")
    @ApiResponse(responseCode = "200", description = "Category created successfully")
    @ApiResponse(responseCode = "400", description = "Error in the request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping(CATEGORY + "/category")
    public ResponseEntity<?> PostCategory(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader,
            @RequestBody BaseCaterogyDTO category
    ) {
        try{
            // Valida el header de autorización
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(authorizationHeader);
            // service
            return ResponseEntity.ok(categoryService.save(headerValidationDTO, category ));
        }catch (UserBadRequest e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (CustomAuthorizationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Update a category")
    @ApiResponse(responseCode = "200", description = "Category updated successfully")
    @ApiResponse(responseCode = "400", description = "Error in the request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PatchMapping(CATEGORY + "/category")
    public ResponseEntity<?> PutCategory(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader,
            @RequestBody BaseCaterogyDTO category
    ) {
        try{
            // Valida el header de autorización
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(authorizationHeader);
            // service
            categoryService.update(headerValidationDTO, category);
            return ResponseEntity.ok("Category updated successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Delete a category")
    @ApiResponse(responseCode = "200", description = "Category deleted successfully")
    @ApiResponse(responseCode = "400", description = "Error in the request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @DeleteMapping(CATEGORY + "/category/{id}")
    public ResponseEntity<?> DeleteCategory(
            @RequestHeader(name="Authorization", required = false) String authorizationHeader,
            @PathVariable String id
    ) {
        try{
            // Valida el header de autorización
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(authorizationHeader);
            // service
            categoryService.deleted(headerValidationDTO, id);
            return ResponseEntity.ok("Category deleted successfully");
        }catch (UserNotExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete a book from a category")
    @ApiResponse(responseCode = "200", description = "Book deleted from category successfully")
    @ApiResponse(responseCode = "400", description = "Error in the request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @DeleteMapping(CATEGORY + "/category/Book")
    public ResponseEntity<?> DeletedCategory (
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader,
            @RequestBody BaseCaterogyDTO category
    ){
        try{
            // Valida el header de autorización
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(authorizationHeader);
            // service
            categoryService.deletedBook(headerValidationDTO, category);
            return ResponseEntity.ok("Category deleted successfully");
        }catch (UserNotExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
