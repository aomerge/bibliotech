package com.aomerge.userservices.config.exeptions;

import com.aomerge.userservices.config.validation.global.HeaderValidationDTO;
import jakarta.validation.ConstraintViolation;
import org.json.JSONObject;

import java.util.Set;

public class CustomAuthorizationException extends RuntimeException{
    private int status;
    private String error;
    private final ErrorDetails errorDetails;

    public CustomAuthorizationException(int status, Set<ConstraintViolation<HeaderValidationDTO>> violations) {
        super("Validation failed");
        this.status = status;
        JSONObject json = new JSONObject();
        for (ConstraintViolation<?> violation : violations) {
            json.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        this.errorDetails = new ErrorDetails(status, json.toString());
    }

    public CustomAuthorizationException(int status, String message) {
        super(message);
        this.status = status;
        this.error = message;
        this.errorDetails = new ErrorDetails(status, message);
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
    @Override
    public String getMessage() {
        return errorDetails.toJson();
    }
}
