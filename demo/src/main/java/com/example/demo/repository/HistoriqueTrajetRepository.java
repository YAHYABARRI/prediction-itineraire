package com.example.demo.repository;

import com.example.demo.models.HistoriqueTrajet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriqueTrajetRepository extends JpaRepository<HistoriqueTrajet, Long> {
}