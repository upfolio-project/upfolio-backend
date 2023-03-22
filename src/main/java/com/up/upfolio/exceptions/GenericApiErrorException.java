package com.up.upfolio.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

import java.time.OffsetDateTime;

public class GenericApiErrorException extends RuntimeException {
    public static GenericApiErrorException NOT_IMPLEMENTED = new GenericApiErrorException(501, "This feature is not implemented yet");

    @Getter
    private final int status;

    @Getter
    private final String text;

    @Getter
    private final OffsetDateTime timestamp;

    public GenericApiErrorException(String badRequestMessage) {
        this(HttpServletResponse.SC_BAD_REQUEST, badRequestMessage);
    }

    public GenericApiErrorException(int status, String message) {
        super(message);

        this.status = status;
        this.text = message;
        this.timestamp = OffsetDateTime.now();
    }

    @Override
    public String getMessage() {
        return String.format("%s (%d)", text, status);
    }
}
