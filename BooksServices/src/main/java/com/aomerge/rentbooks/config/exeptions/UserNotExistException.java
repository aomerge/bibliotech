package com.aomerge.rentbooks.config.exeptions;
import com.aomerge.rentbooks.config.exeptions.ErrorDetails;

public class UserNotExistException extends Exception {
    private final ErrorDetails errorDetails;

    public UserNotExistException(int statusCode, String message) {
        super(message);
        this.errorDetails = new ErrorDetails(statusCode, message);
    }
    public UserNotExistException(int statusCode, String message, Throwable cause) {
        super(message, cause);
        this.errorDetails = new ErrorDetails(statusCode, message);
    }

    public UserNotExistException(int statusCode, Throwable cause) {
        super(cause);
        this.errorDetails = new ErrorDetails(statusCode, cause.getMessage());
    }


    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    @Override
    public String getMessage() {
        return errorDetails.toJson();
    }
}
