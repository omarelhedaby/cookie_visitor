package com.quantcast.assessment.cookietracker.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles exceptions when no cookies are found.
     *
     * @param ex The exception that was thrown.
     * @return A ResponseEntity with an error message and 404 status.
     */

    @ExceptionHandler(CookieVisitsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCookieVisitsNotFoundException(CookieVisitsNotFoundException ex) {
        logger.error("No cookies found error: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handles InvalidDateFormatException thrown when the date format is invalid.
     *
     * @param ex The exception.
     * @return A ResponseEntity with the error message and 400 status code.
     */
    @ExceptionHandler(InvalidDateFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleInvalidDateFormatException(InvalidDateFormatException ex) {
        logger.error("Invalid date format error: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Handles generic exceptions.
     *
     * @param ex The exception that was thrown.
     * @return A ResponseEntity with an error message and 500 status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        logger.error("Unexpected error occurred: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
