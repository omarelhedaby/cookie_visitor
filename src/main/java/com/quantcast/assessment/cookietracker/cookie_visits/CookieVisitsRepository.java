package com.quantcast.assessment.cookietracker.cookie_visits;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieVisitsRepository extends JpaRepository<CookieVisits, Long>{

    @Query("SELECT new com.quantcast.assessment.cookietracker.cookie_visits.CookieVisitsResponse(cv.cookieId, COUNT(cv)) " +
    "FROM CookieVisits cv " +
    "WHERE cast(cv.timestamp as date) = :targetDate " +
    "GROUP BY cv.cookieId " +
    "HAVING COUNT(cv) = (" +
    "   SELECT MAX(count) FROM (" +
    "       SELECT COUNT(*) AS count " +
    "       FROM CookieVisits cv2 " +
    "       WHERE cast(cv2.timestamp as date) = :targetDate " +
    "       GROUP BY cv2.cookieId" +
    "   ) AS counts" +
    ")")
    List<CookieVisitsResponse> findMostVisitedCookiesByDate(@Param("targetDate") LocalDate targetDate);

}
