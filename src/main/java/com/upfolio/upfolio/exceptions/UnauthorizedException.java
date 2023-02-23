package com.upfolio.upfolio.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

public class UnauthorizedException extends RuntimeException {
    @Getter
    private final int status = HttpServletResponse.SC_UNAUTHORIZED;

    @Getter
    private final String text = "Unauthorized. Please proceed to the login page";
}
