package com.example.demo.models;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "itineraires")
public class Itineraire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Double distance;
    private Double tempsEstime;
    private LocalDateTime dateCreation;

    // 🔥 relation avec utilisateur
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    // 🔥 point de départ
    @ManyToOne
    @JoinColumn(name = "lieu_depart_id")
    private Lieu lieuDepart;

    // 🔥 point d’arrivée
    @ManyToOne
    @JoinColumn(name = "lieu_arrivee_id")
    private Lieu lieuArrivee;

    // 🔥 OPTIONNEL (niveau avancé)
    @ManyToMany
    @JoinTable(
            name = "itineraire_points",
            joinColumns = @JoinColumn(name = "itineraire_id"),
            inverseJoinColumns = @JoinColumn(name = "lieu_id")
    )
    private List<Lieu> points;

    public Itineraire() {
    }

    public Itineraire(String nom, Double distance, Double tempsEstime,
                      LocalDateTime dateCreation, Utilisateur utilisateur,
                      Lieu lieuDepart, Lieu lieuArrivee, List<Lieu> points) {

        this.nom = nom;
        this.distance = distance;
        this.tempsEstime = tempsEstime;
        this.dateCreation = dateCreation;
        this.utilisateur = utilisateur;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getTempsEstime() {
        return tempsEstime;
    }

    public void setTempsEstime(Double tempsEstime) {
        this.tempsEstime = tempsEstime;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Lieu getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(Lieu lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public Lieu getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(Lieu lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public List<Lieu> getPoints() {
        return points;
    }

    public void setPoints(List<Lieu> points) {
        this.points = points;
    }
    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
    }
}