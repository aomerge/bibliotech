package com.aomerge.rentbooks.config.exeptions;

public class CustomAuthorizationException extends RuntimeException{
    private int status;
    private String error;
    private final ErrorDetails errorDetails;

    public CustomAuthorizationException(String message, int status, String error) {
        super(message);
        this.status = status;
        this.error = error;
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
