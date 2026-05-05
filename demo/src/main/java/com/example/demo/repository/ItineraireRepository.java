package com.example.demo.repository;


import com.example.demo.models.Itineraire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraireRepository extends JpaRepository<Itineraire, Long> {
}