package com.finora.backend.common.exception;

// The request conflicts with the current state of a resource
// for example a payment above the outstanding balance.

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
