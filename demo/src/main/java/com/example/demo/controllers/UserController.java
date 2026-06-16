package com.example.demo.controllers;

import com.example.demo.models.SearchHistory;
import com.example.demo.repository.SearchHistoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final SearchHistoryRepository searchHistoryRepository;

    public UserController(SearchHistoryRepository searchHistoryRepository) {
        this.searchHistoryRepository = searchHistoryRepository;
    }

    @GetMapping("/{userId}/history")
    public ResponseEntity<List<SearchHistory>> getUserHistory(@PathVariable Long userId) {
        List<SearchHistory> history = searchHistoryRepository.findByUserIdOrderBySearchedAtDesc(userId);
        return ResponseEntity.ok(history);
    }
}
