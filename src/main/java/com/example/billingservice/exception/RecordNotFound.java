package com.example.billingservice.exception;

public class RecordNotFound extends RuntimeException {
    public RecordNotFound(String message) {
        super(message);
    }
}
