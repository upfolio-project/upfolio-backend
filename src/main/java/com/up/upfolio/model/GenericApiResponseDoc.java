package com.up.upfolio.model;

import com.up.upfolio.model.errors.GenericApiError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = "200")
@ApiResponse(responseCode = "400", description = "Bad API request", content = @Content(schema = @Schema(implementation = GenericApiError.class)))
@ApiResponse(responseCode = "401", description = "Authorization issues", content = @Content(schema = @Schema(implementation = GenericApiError.class)))
@ApiResponse(responseCode = "403", description = "No access to requested resource", content = @Content(schema = @Schema(implementation = GenericApiError.class)))
@ApiResponse(responseCode = "404", description = "Requested resource is not found", content = @Content(schema = @Schema(implementation = GenericApiError.class)))
public @interface GenericApiResponseDoc {
}
