package com.quantcast.assessment.cookietracker.cookie_visits;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantcast.assessment.cookietracker.exception.CookieVisitsNotFoundException;

@Service
public class CookieVisitsService {
    private static final Logger logger = LoggerFactory.getLogger(CookieVisitsService.class);

    @Autowired
    private CookieVisitsRepository cookieVisitsRepository;

    /**
     * Retrieves the most visited cookies for a given date.
     *
     * @param targetDate The date for which to retrieve the most visited cookies.
     * @return A list of the most visited cookies on the given date.
     * @throws NoCookiesFoundException if no cookies are found for the given date.
     */
    public List<CookieVisitsResponse> getMostVisitedCookiesByDate(LocalDate targetDate) {
        logger.info("Fetching most visited cookies for date: {}", targetDate);

        List<CookieVisitsResponse> result = cookieVisitsRepository.findMostVisitedCookiesByDate(targetDate);
        
        if (result.isEmpty()) {
            throw new CookieVisitsNotFoundException("No cookie visits found for date: " + targetDate);
        }

        logger.info("Found {} most visited cookies for date: {}", result.size(), targetDate);
        return result;
    }
}
