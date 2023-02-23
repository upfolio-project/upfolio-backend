package com.upfolio.upfolio.model.api.response;

import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class BaseApiResponse {
    private final OffsetDateTime timestamp;

    protected BaseApiResponse() {
        timestamp = OffsetDateTime.now();
    }
}
