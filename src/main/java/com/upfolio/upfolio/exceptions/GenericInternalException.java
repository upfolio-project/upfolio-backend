package com.upfolio.upfolio.exceptions;

public class GenericInternalException extends RuntimeException {
    private final String message;

    public GenericInternalException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
