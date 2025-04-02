package com.quantcast.assessment.cookietracker.cookievisits;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.quantcast.assessment.cookietracker._controllers.CookieVisitsController;
import com.quantcast.assessment.cookietracker.cookie_visits.CookieVisitsService;

@WebMvcTest
public class CookieVisitsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CookieVisitsService cookieVisitsService;

    @Test
    void shouldReturnBadRequestForInvalidDateFormat() throws Exception {
        mockMvc.perform(get("/api/cookie/most-visited/2024-10"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid date format. Please use YYYY-MM-DD."));
    }

}
