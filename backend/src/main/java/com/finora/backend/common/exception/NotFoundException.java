package com.finora.backend.common.exception;

// The requested resource does not exist, or does not belong to the caller's business.

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

}
