package com.aomerge.rentbooks.config.validation;

import jakarta.validation.constraints.*;


public class BookValidationDTO {
    @NotNull(message = "Title is required")
    @NotBlank(message = "Title is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Title must be alphanumeric")
    @Size(min = 5, max = 50, message = "Title must be between 1 and 100 characters")
    private String title;
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Title must be alphanumeric")
    private String description;
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Title must be alphanumeric")
    private String author;
    @PastOrPresent(message = "Year must be in the past")
    @DecimalMax(value = "2024", message = "Year must be in the past")
    @DecimalMin(value = "1500", message = "Year must be in the past")
    private int year;
    @NotNull
    private int ISBN;
}
