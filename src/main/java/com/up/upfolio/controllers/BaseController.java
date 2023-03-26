package com.up.upfolio.controllers;

import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.exceptions.UnauthorizedException;
import com.up.upfolio.model.GenericApiResponseDoc;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import com.up.upfolio.model.errors.GenericApiError;
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
@GenericApiResponseDoc
@Slf4j
public class BaseController {
        @ExceptionHandler(GenericApiErrorException.class)
        public GenericApiError handleGenericException(HttpServletResponse response, GenericApiErrorException e) {
                response.setStatus(e.getErrorDescriptor().getStatus());

                return new GenericApiError(e.getErrorDescriptor());
        }

        @ExceptionHandler(UnauthorizedException.class)
        public GenericApiError handleUnauthorizedException(HttpServletResponse response) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                return new GenericApiError(ErrorDescriptor.UNAUTHORIZED);
        }

        @ExceptionHandler({RuntimeException.class})
        public GenericApiError handleAllError(HttpServletResponse response, RuntimeException e) {
                log.error("BaseController caught runtime exception", e);

                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return new GenericApiError(ErrorDescriptor.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler({HttpMessageNotReadableException.class})
        public GenericApiError handleNotReadable(HttpServletResponse response, HttpMessageNotReadableException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                return new GenericApiError(ErrorDescriptor.BAD_REQUEST);
        }
}
