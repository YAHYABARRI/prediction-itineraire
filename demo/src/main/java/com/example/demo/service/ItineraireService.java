package com.example.demo.service;

import com.example.demo.models.Itineraire;
import com.example.demo.repository.ItineraireRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraireService {

    private final ItineraireRepository itineraireRepository;

    public ItineraireService(ItineraireRepository itineraireRepository) {
        this.itineraireRepository = itineraireRepository;
    }

    public List<Itineraire> getAllItineraires() {
        return itineraireRepository.findAll();
    }

    public Optional<Itineraire> getItineraireById(Long id) {
        return itineraireRepository.findById(id);
    }

    public Itineraire saveItineraire(Itineraire itineraire) {
        return itineraireRepository.save(itineraire);
    }

    public void deleteItineraire(Long id) {
        itineraireRepository.deleteById(id);
    }
}