package com.example.demo.repository;

import com.example.demo.models.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    List<SearchHistory> findByUserIdOrderBySearchedAtDesc(Long userId);

    @Query("""
        SELECT h.destinationName, COUNT(h.destinationName)
        FROM SearchHistory h
        GROUP BY h.destinationName
        ORDER BY COUNT(h.destinationName) DESC
    """)
    List<Object[]> findMostVisitedPlaces();

    @Query(value = "SELECT DATE(searched_at) as date, COUNT(*) as count FROM search_history GROUP BY DATE(searched_at) ORDER BY DATE(searched_at) DESC LIMIT 7", nativeQuery = true)
    List<Object[]> findSearchesPerDay();

    @Query(value = "SELECT CONCAT('Week ', WEEK(searched_at, 1)) as week, COUNT(*) as count FROM search_history GROUP BY WEEK(searched_at, 1) ORDER BY WEEK(searched_at, 1) DESC LIMIT 4", nativeQuery = true)
    List<Object[]> findPredictionsPerWeek();

    @Query(value = "SELECT u.email, COUNT(h.id) as count FROM search_history h JOIN utilisateur u ON h.user_id = u.id GROUP BY u.email ORDER BY count DESC LIMIT 5", nativeQuery = true)
    List<Object[]> findTopUsers();
}