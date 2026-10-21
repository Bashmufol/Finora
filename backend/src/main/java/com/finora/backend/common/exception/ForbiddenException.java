package com.finora.backend.common.exception;

// The caller is authenticated but not allowed to perform this action, usually a cross-business access attempt.
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
   }
}
