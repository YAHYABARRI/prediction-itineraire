package com.example.demo.repository;

import com.example.demo.models.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    List<SearchHistory> findByUserId(Long userId);

    @Query("""
        SELECT h.destinationName, COUNT(h.destinationName)
        FROM SearchHistory h
        GROUP BY h.destinationName
        ORDER BY COUNT(h.destinationName) DESC
    """)
    List<Object[]> findMostVisitedPlaces();
}