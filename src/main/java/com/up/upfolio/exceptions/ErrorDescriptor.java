package com.up.upfolio.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public enum ErrorDescriptor {
    INVALID_OTP_CODE(HttpServletResponse.SC_FORBIDDEN, "Invalid OTP code"),
    ACCOUNT_NOT_FOUND("The account with this phone number is not found"),
    INCORRECT_PASSWORD(HttpServletResponse.SC_FORBIDDEN, "Password is incorrect"),
    BAD_PHONE_NUMBER("Bad phone number"),
    REGISTRATION_STEPS_FAULT("Registration steps fault, please reload the page"),
    OTP_ATTEMPTS_EXCEEDED("Too many OTP attempts"),
    BAD_USER_NAME("Bad user name"),
    REGISTRATION_TOKEN_IS_NOT_PROVIDED(HttpServletResponse.SC_FORBIDDEN, "Missing valid registration token"),
    PROFILE_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "Profile not found"),
    ACCOUNT_IS_DEACTIVATED("Your account is deactivated. Please contact the support team"),
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"),
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error"),
    BAD_REQUEST("Invalid request. Please try reloading the tab"),
    SESSION_EXPIRED(HttpServletResponse.SC_UNAUTHORIZED, "Session is expired. Please sign in again");

    private final String description;
    private final int status;

    private static final int DEFAULT_CODE = HttpServletResponse.SC_BAD_REQUEST;

    ErrorDescriptor(int status, String description) {
        this.description = description;
        this.status = status;
    }

    ErrorDescriptor(String description) {
        this.description = description;
        this.status = DEFAULT_CODE;
    }

    public String getDescription() {
        return this.description;
    }

    public int getStatus() {
        return status;
    }
}
