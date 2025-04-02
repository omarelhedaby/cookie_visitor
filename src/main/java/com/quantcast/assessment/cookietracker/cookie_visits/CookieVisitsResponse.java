package com.quantcast.assessment.cookietracker.cookie_visits;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CookieVisitsResponse {

    private String cookieId;
    private Long visitCount;
}
