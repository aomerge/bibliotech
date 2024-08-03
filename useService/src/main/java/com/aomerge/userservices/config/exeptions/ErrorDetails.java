package com.aomerge.userservices.config.exeptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ErrorDetails {
    @Setter
    private int statusCode;
    @Getter
    private String message;
    private Object messageObject;
    private LocalDateTime timesTamp;

    public ErrorDetails(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timesTamp = LocalDateTime.now();
    }
    public ErrorDetails(int statusCode, Throwable cause) {
        this.statusCode = statusCode;
        this.message = cause.getMessage();
        this.timesTamp = LocalDateTime.now();
    }
    public ErrorDetails( String message) {
        this.statusCode = 400;
        this.message = message;
        this.timesTamp = LocalDateTime.now();
    }

    public String toJson() {
        return String.format("{\"statusCode\": %d, \"message\": \"%s\", \"timesTamp\": \"%s\"}", statusCode, message, timesTamp.toString());
    }
}
