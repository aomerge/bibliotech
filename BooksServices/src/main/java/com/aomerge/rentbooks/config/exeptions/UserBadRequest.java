package com.aomerge.rentbooks.config.exeptions;

import com.aomerge.rentbooks.config.exeptions.ErrorDetails;
import com.aomerge.rentbooks.config.validation.books.BaseBookDTO;
import jakarta.validation.ConstraintViolation;
import org.json.JSONObject;

import java.util.Set;

public class UserBadRequest extends RuntimeException {
    private final ErrorDetails errorDetails;

    public UserBadRequest(int statusCode, String message) {
        super(message);
        this.errorDetails = new ErrorDetails(statusCode, message);
    }
    public UserBadRequest(Set<ConstraintViolation<BaseBookDTO>> violations) {
        super("Validation failed");
        JSONObject json = new JSONObject();
        for (ConstraintViolation<?> violation : violations) {
            json.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        this.errorDetails = new ErrorDetails(json.toString());
    }
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }
    @Override
    public String getMessage() {
        return errorDetails.toJson();
    }
}
