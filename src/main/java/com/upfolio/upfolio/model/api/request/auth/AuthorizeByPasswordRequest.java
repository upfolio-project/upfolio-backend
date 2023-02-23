package com.upfolio.upfolio.model.api.request.auth;

public record AuthorizeByPasswordRequest(String phoneNumber, String password) {
}
