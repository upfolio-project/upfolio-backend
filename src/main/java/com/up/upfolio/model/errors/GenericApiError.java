package com.up.upfolio.model.errors;

import com.up.upfolio.exceptions.ErrorDescriptor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Schema(name = "ApiError", requiredMode = Schema.RequiredMode.REQUIRED)
public class GenericApiError {
    private int status;
    private String text;
    private OffsetDateTime timestamp;
    private ErrorDescriptor error;

    public GenericApiError(ErrorDescriptor errorDescriptor) {
        this.timestamp = OffsetDateTime.now();

        this.error = errorDescriptor;
        this.text = errorDescriptor.getDescription();
        this.status = errorDescriptor.getStatus();
    }

    public GenericApiError() {
    }
}

