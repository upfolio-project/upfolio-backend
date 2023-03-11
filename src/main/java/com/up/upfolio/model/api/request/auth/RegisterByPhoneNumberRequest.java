package com.up.upfolio.model.api.request.auth;

public record RegisterByPhoneNumberRequest(String registerToken, String phoneNumber) {
}
