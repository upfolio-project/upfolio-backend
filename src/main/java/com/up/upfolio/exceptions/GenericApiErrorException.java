package com.up.upfolio.exceptions;

import lombok.Getter;


public class GenericApiErrorException extends RuntimeException {
    @Getter
    private final ErrorDescriptor errorDescriptor;

    public GenericApiErrorException(ErrorDescriptor errorDescriptor) {
        this.errorDescriptor = errorDescriptor;
    }
}
