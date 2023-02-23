package com.up.upfolio.model.api.request.auth;

public record AuthorizeByPasswordRequest(String phoneNumber, String password) {
}
