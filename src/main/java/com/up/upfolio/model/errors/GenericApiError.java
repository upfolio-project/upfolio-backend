package com.up.upfolio.model.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.up.upfolio.exceptions.ErrorBulk;
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
    private final ErrorBulk error;

    public GenericApiError(ErrorBulk errorBulk) {
        this.timestamp = OffsetDateTime.now();

        this.error = errorBulk;
        this.text = errorBulk.getDescription();
        this.status = errorBulk.getStatus();
    }
}

