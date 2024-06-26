package com.up.upfolio.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.up.upfolio.exceptions.ErrorDescriptor;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.up.upfolio.model.errors.GenericApiError;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUnauthorizedController implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        log.debug("Unauthorized");
        objectMapper.writeValue(response.getOutputStream(), new GenericApiError(ErrorDescriptor.UNAUTHORIZED));
    }
}
