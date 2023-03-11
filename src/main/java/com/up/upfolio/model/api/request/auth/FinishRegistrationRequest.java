package com.up.upfolio.model.api.request.auth;

public record FinishRegistrationRequest(String registerToken, String firstName, String lastName, String password) {
}
