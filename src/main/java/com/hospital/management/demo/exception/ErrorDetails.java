package com.hospital.management.demo.exception;

import java.time.LocalDate;

public class ErrorDetails {
    private LocalDate timeStamp;

    public ErrorDetails(LocalDate timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    private String message;
    private String details;

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public ErrorDetails setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorDetails setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public ErrorDetails setDetails(String details) {
        this.details = details;
        return this;
    }
}