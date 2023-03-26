package com.up.upfolio.exceptions;

import lombok.Getter;


public class GenericApiErrorException extends RuntimeException {
    @Getter
    private final ErrorBulk errorBulk;

    public GenericApiErrorException(ErrorBulk errorBulk) {
        this.errorBulk = errorBulk;
    }
}
