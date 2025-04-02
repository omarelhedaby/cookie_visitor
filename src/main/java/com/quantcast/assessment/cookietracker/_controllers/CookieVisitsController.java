package com.quantcast.assessment.cookietracker._controllers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quantcast.assessment.cookietracker.cookie_visits.CookieVisitsResponse;
import com.quantcast.assessment.cookietracker.cookie_visits.CookieVisitsService;
import com.quantcast.assessment.cookietracker.exception.ErrorResponse;
import com.quantcast.assessment.cookietracker.exception.InvalidDateFormatException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/cookie")
public class CookieVisitsController {
    
    private static final Logger logger = LoggerFactory.getLogger(CookieVisitsController.class);

    @Autowired
    private CookieVisitsService cookieVisitService;

    /**
     * Retrieves the most visited cookies for a given date.
     * 
     * @param date The target date in YYYY-MM-DD format.
     * @return List of the most visited cookies.
     */
    @Operation(
    summary = "Get most visited cookies for a given date", 
    description = "Returns a list of cookies with the highest visits on the specified date."
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Successfully retrieved most visited cookies",
    content = @Content(mediaType = "application/json", 
        schema = @Schema(implementation = CookieVisitsResponse[].class))),
    @ApiResponse(responseCode = "400", description = "Invalid date format",
        content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ErrorResponse.class))),
    @ApiResponse(responseCode = "404", description = "No cookies found for the given date",
        content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ErrorResponse.class)))
})
    @GetMapping("/most-visited/{targetDate}")
    public ResponseEntity<List<CookieVisitsResponse>> getMostVisitedCookiesByDate(
        @Parameter(
                description = "Date in format YYYY-MM-DD",
                example = "2024-04-02",
                required = true,
                schema = @Schema(type = "string", format = "date")
        )
        @PathVariable("targetDate") String targetDate) {

        try {
            LocalDate date = LocalDate.parse(targetDate);
            List<CookieVisitsResponse> result = cookieVisitService.getMostVisitedCookiesByDate(date);
            return ResponseEntity.ok(result);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format. Please use YYYY-MM-DD.");
        } catch (Exception e) {
            throw e;
        }
    }

}
