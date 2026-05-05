package com.example.demo.controllers;

import com.example.demo.models.Itineraire;
import com.example.demo.service.ItineraireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itineraires")
@CrossOrigin("*")
public class ItineraireController {

    private final ItineraireService itineraireService;

    public ItineraireController(ItineraireService itineraireService) {
        this.itineraireService = itineraireService;
    }

    @GetMapping
    public List<Itineraire> getAllItineraires() {
        return itineraireService.getAllItineraires();
    }

    @GetMapping("/{id}")
    public Optional<Itineraire> getItineraireById(@PathVariable Long id) {
        return itineraireService.getItineraireById(id);
    }

    @PostMapping
    public Itineraire createItineraire(@RequestBody Itineraire itineraire) {
        return itineraireService.saveItineraire(itineraire);
    }

    @DeleteMapping("/{id}")
    public void deleteItineraire(@PathVariable Long id) {
        itineraireService.deleteItineraire(id);
    }
}