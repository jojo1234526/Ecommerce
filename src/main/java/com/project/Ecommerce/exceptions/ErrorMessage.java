package com.project.Ecommerce.exceptions;

import java.util.Date;

public class ErrorMessage {
    private Date timestamp;
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(Date timestamp, String message) {
        super();
        this.timestamp = timestamp;
        this.message = message;
    }

    // Getters and setters omitted for brevity
}
