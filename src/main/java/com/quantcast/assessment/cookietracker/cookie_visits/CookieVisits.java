package com.quantcast.assessment.cookietracker.cookie_visits;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cookie_visits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookieVisits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cookieId;

    private LocalDateTime timestamp;
}
