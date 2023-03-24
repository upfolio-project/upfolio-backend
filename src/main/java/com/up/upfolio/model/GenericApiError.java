package com.up.upfolio.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class GenericApiError {
    private final int status;
    private final String text;
    private final OffsetDateTime timestamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage = null;

    public GenericApiError(String badRequestMessage) {
        this(HttpServletResponse.SC_BAD_REQUEST, badRequestMessage);
    }

    public GenericApiError(int status, String text) {
        this.status = status;
        this.text = text;
        this.timestamp = OffsetDateTime.now();
    }
}

