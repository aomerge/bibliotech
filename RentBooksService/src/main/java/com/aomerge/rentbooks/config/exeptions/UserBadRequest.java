package com.aomerge.rentbooks.config.exeptions;

import com.aomerge.rentbooks.config.validation.baseBookExisten.BaseBookInExistenDTO;
import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import org.json.JSONObject;

import java.util.Set;

@Getter
public class UserBadRequest extends RuntimeException {
    private final ErrorDetails errorDetails;

    public UserBadRequest(int statusCode, String message) {
        super(message);
        this.errorDetails = new ErrorDetails(statusCode, message);
    }
    public UserBadRequest(int statusCode, Set<ConstraintViolation<BaseBookInExistenDTO>> violations) {
        super("Validation failed");
        JSONObject json = new JSONObject();
        for (ConstraintViolation<?> violation : violations) {
            json.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        this.errorDetails = new ErrorDetails(statusCode, json.toString());
    }
    public UserBadRequest(Set<ConstraintViolation<?>> violations) {
        super("Validation failed");
        JSONObject json = new JSONObject();
        for (ConstraintViolation<?> violation : violations) {
            json.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        this.errorDetails = new ErrorDetails(json.toString());
    }

    @Override
    public String getMessage() {
        return errorDetails.toJson();
    }
}
