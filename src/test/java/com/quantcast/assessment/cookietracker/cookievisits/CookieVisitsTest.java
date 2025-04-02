package com.quantcast.assessment.cookietracker.cookievisits;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.quantcast.assessment.cookietracker.cookie_visits.CookieVisits;
import com.quantcast.assessment.cookietracker.cookie_visits.CookieVisitsRepository;
import com.quantcast.assessment.cookietracker.cookie_visits.CookieVisitsResponse;
import com.quantcast.assessment.cookietracker.cookie_visits.CookieVisitsService;
import com.quantcast.assessment.cookietracker.exception.CookieVisitsNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import(CookieVisitsService.class)
public class CookieVisitsTest {

    @Autowired
    private CookieVisitsRepository cookieVisitsRepository;
    @Autowired
    private CookieVisitsService cookieVisitsService;

    @BeforeEach
    void setUp() {
        cookieVisitsRepository.save(new CookieVisits(1L,"cookie1", LocalDateTime.of(2024, 4, 1, 10, 0)));
        cookieVisitsRepository.save(new CookieVisits(2L,"cookie1", LocalDateTime.of(2024, 4, 1, 12, 0)));
        cookieVisitsRepository.save(new CookieVisits(3L,"cookie2", LocalDateTime.of(2024, 4, 1, 14, 0)));
        cookieVisitsRepository.save(new CookieVisits(4L,"cookie2", LocalDateTime.of(2024, 4, 1, 15, 0)));
        cookieVisitsRepository.save(new CookieVisits(5L,"cookie3", LocalDateTime.of(2024, 4, 1, 16, 0)));
        cookieVisitsRepository.save(new CookieVisits(6L, "cookie4", LocalDateTime.of(2024, 4, 2, 10, 0)));
        cookieVisitsRepository.save(new CookieVisits(7L, "cookie5", LocalDateTime.of(2024, 4, 2, 11, 0)));
        cookieVisitsRepository.save(new CookieVisits(8L, "cookie6", LocalDateTime.of(2024, 4, 2, 12, 0)));
    }

     @Test
    void shouldReturnMostVisitedCookiesForGivenDate() {
        LocalDate targetDate = LocalDate.of(2024, 4, 1);

        List<CookieVisitsResponse> result = cookieVisitsRepository.findMostVisitedCookiesByDate(targetDate);

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result).extracting(CookieVisitsResponse::getCookieId).containsExactlyInAnyOrder("cookie1", "cookie2");
    }

    @Test
    void shouldReturnAllCookiesWhenEachVisitsOnce() {
        LocalDate targetDate = LocalDate.of(2024, 4, 2);
        List<CookieVisitsResponse> result = cookieVisitsRepository.findMostVisitedCookiesByDate(targetDate);

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(3);
        assertThat(result).extracting(CookieVisitsResponse::getCookieId).containsExactlyInAnyOrder("cookie4", "cookie5", "cookie6");
    }

    @Test
    void shouldThrowExceptionWhenNoCookiesFoundForGivenDate() {
        LocalDate targetDate = LocalDate.of(2025, 1, 1);

        assertThatThrownBy(() -> cookieVisitsService.getMostVisitedCookiesByDate(targetDate))
        .isInstanceOf(CookieVisitsNotFoundException.class)
        .hasMessageContaining("No cookie visits found for date: 2025-01-01");
    }

}
