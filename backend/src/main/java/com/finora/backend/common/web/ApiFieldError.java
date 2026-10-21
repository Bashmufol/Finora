package com.finora.backend.common.web;

// One field-level validation failure, matched to the "errors" array in docs/openapi.yaml.
public record ApiFieldError (String field, String message){
}
