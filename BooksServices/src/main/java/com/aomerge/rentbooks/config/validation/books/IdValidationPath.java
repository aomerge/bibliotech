package com.aomerge.rentbooks.config.validation.books;

import jakarta.validation.constraints.NotNull;

public class IdValidationPath {
    @NotNull(message = "Id is required")
    private String id;

    public @NotNull(message = "Id is required") String getId() {
        return id;
    }

    public void setId(@NotNull(message = "Id is required") String id) {
        this.id = id;
    }
}
