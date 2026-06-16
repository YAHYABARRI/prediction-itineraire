package com.example.demo.controllers;



import com.example.demo.models.Itineraire;
import com.example.demo.models.SearchHistory;
import com.example.demo.models.Utilisateur;
import com.example.demo.repository.SearchHistoryRepository;
import com.example.demo.repository.UtilisateurRepository;
import com.example.demo.service.ItineraireService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private final UtilisateurRepository utilisateurRepository;
    private final SearchHistoryRepository searchHistoryRepository;
    private final ItineraireService itineraireService;

    public AdminController(
            UtilisateurRepository utilisateurRepository,
            SearchHistoryRepository searchHistoryRepository,
            ItineraireService itineraireService
    ) {
        this.utilisateurRepository = utilisateurRepository;
        this.searchHistoryRepository = searchHistoryRepository;
        this.itineraireService = itineraireService;
    }

    @GetMapping("/users")
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    @PutMapping("/users/{id}/activate")
    public Utilisateur activateUser(@PathVariable Long id) {
        Utilisateur user = utilisateurRepository.findById(id).orElseThrow();
        user.setActive(true);
        return utilisateurRepository.save(user);
    }

    @PutMapping("/users/{id}/deactivate")
    public Utilisateur deactivateUser(@PathVariable Long id) {
        Utilisateur user = utilisateurRepository.findById(id).orElseThrow();
        user.setActive(false);
        return utilisateurRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        utilisateurRepository.deleteById(id);
    }
    @GetMapping("/history")
    public List<SearchHistory> getAllHistory() {
        return searchHistoryRepository.findAll();
    }

    @GetMapping("/itineraries")
    public List<Itineraire> getAllItineraires() {
        return itineraireService.getAllItineraires();
    }

    @GetMapping("/popular-places")
    public List<Object[]> getPopularPlaces() {
        return searchHistoryRepository.findMostVisitedPlaces();
    }
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        stats.put("totalUsers", utilisateurRepository.count());
        stats.put("totalSearches", searchHistoryRepository.count());
        stats.put("popularPlaces", searchHistoryRepository.findMostVisitedPlaces());

        return stats;
    }

    @GetMapping("/analytics")
    public Map<String, Object> getAnalyticsData() {
        Map<String, Object> analytics = new HashMap<>();

        analytics.put("searchesPerDay", searchHistoryRepository.findSearchesPerDay());
        analytics.put("mostVisitedPlaces", searchHistoryRepository.findMostVisitedPlaces());
        analytics.put("predictionsPerWeek", searchHistoryRepository.findPredictionsPerWeek());
        analytics.put("topUsers", searchHistoryRepository.findTopUsers());

        return analytics;
    }
}