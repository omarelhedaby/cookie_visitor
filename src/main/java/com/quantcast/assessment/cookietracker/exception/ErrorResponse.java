package com.quantcast.assessment.cookietracker.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Response format for error messages")
public class ErrorResponse {

    @Schema(description = "The error status code", example = "status code")
    private int statusCode;

    @Schema(description = "The error message", example = "error message")
    private String message;
}
