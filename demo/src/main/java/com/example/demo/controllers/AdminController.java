package com.example.demo.controllers;



import com.example.demo.models.Itineraire;
import com.example.demo.models.SearchHistory;
import com.example.demo.models.Utilisateur;
import com.example.demo.repository.SearchHistoryRepository;
import com.example.demo.repository.UtilisateurRepository;
import com.example.demo.service.ItineraireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private final UtilisateurRepository utilisateurRepository;

    private final SearchHistoryRepository searchHistoryRepository;

    public AdminController(
            UtilisateurRepository utilisateurRepository,
            SearchHistoryRepository searchHistoryRepository
    ) {
        this.utilisateurRepository = utilisateurRepository;
        this.searchHistoryRepository = searchHistoryRepository;
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

    @GetMapping("/popular-places")
    public List<Object[]> getPopularPlaces() {
        return searchHistoryRepository.findMostVisitedPlaces();
    }
}