package com.aomerge.rentbooks.config.validation.global;

import jakarta.validation.constraints.NotNull;

public class HeaderValidationDTO {
    @NotNull(message = "min")
    private String authorizationHeader;

    public @NotNull(message = "min") String getAuthorizationHeader() {
        return authorizationHeader;
    }

    public void setAuthorizationHeader(@NotNull(message = "min") String authorizationHeader) {
        this.authorizationHeader = authorizationHeader;
    }
}
