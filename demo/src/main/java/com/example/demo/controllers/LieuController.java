package com.example.demo.controllers;

import com.example.demo.models.Lieu;
import com.example.demo.repository.LieuRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lieux")
@CrossOrigin("*")
public class LieuController {

    private final LieuRepository lieuRepository;

    public LieuController(LieuRepository lieuRepository) {
        this.lieuRepository = lieuRepository;
    }

    @GetMapping
    public List<Lieu> getAllLieux() {
        return lieuRepository.findAll();
    }

    @PostMapping
    public Lieu createLieu(@RequestBody Lieu lieu) {
        return lieuRepository.save(lieu);
    }
}