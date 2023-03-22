package com.up.upfolio.model.api.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizeByPasswordRequest {
    @JsonProperty(value = "phoneNumber", required = true)
    private String phoneNumber;

    @JsonProperty(value = "phoneNumber", required = true)
    private String password;
}
