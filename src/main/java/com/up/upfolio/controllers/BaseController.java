package com.up.upfolio.controllers;

import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.exceptions.UnauthorizedException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import com.up.upfolio.model.GenericApiError;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@OpenAPIDefinition(info = @Info(
        title = "UpFolio API",
        version = "v1",
        description = "UpFolio main API"),
        servers = {
                @Server(url = "/", description = "Default Server URL"),
        }
)
@Slf4j
public class BaseController {
        @ExceptionHandler(GenericApiErrorException.class)
        public GenericApiError handleGenericException(HttpServletResponse response, GenericApiErrorException e) {
                response.setStatus(e.getStatus());

                return new GenericApiError(e.getStatus(), e.getText());
        }

        @ExceptionHandler(UnauthorizedException.class)
        public GenericApiError handleUnauthorizedException(HttpServletResponse response) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                return new GenericApiError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }

        @ExceptionHandler({RuntimeException.class})
        public GenericApiError handleAllError(HttpServletResponse response, RuntimeException e) {
                log.error("BaseController caught runtime exception", e);

                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return new GenericApiError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal server error was encountered. Please try your request later");
        }

        @ExceptionHandler({HttpMessageNotReadableException.class})
        public GenericApiError handleNotReadable(HttpServletResponse response, HttpMessageNotReadableException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                GenericApiError error = new GenericApiError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request. Please try reloading the tab");
                error.setErrorMessage(e.getMessage());

                return error;
        }
}
