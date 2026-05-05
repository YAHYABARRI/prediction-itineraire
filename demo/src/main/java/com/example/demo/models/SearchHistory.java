package com.example.demo.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utilisateur user;

    private String departureName;
    private Double departureLat;
    private Double departureLon;

    private String destinationName;
    private Double destinationLat;
    private Double destinationLon;

    private Double distance;
    private Double osrmDuration;
    private Double predictedDuration;

    private LocalDateTime searchedAt = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public String getDepartureName() {
        return departureName;
    }

    public void setDepartureName(String departureName) {
        this.departureName = departureName;
    }

    public Double getDepartureLat() {
        return departureLat;
    }

    public void setDepartureLat(Double departureLat) {
        this.departureLat = departureLat;
    }

    public Double getDepartureLon() {
        return departureLon;
    }

    public void setDepartureLon(Double departureLon) {
        this.departureLon = departureLon;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Double getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(Double destinationLat) {
        this.destinationLat = destinationLat;
    }

    public Double getDestinationLon() {
        return destinationLon;
    }

    public void setDestinationLon(Double destinationLon) {
        this.destinationLon = destinationLon;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getOsrmDuration() {
        return osrmDuration;
    }

    public void setOsrmDuration(Double osrmDuration) {
        this.osrmDuration = osrmDuration;
    }

    public Double getPredictedDuration() {
        return predictedDuration;
    }

    public void setPredictedDuration(Double predictedDuration) {
        this.predictedDuration = predictedDuration;
    }

    public LocalDateTime getSearchedAt() {
        return searchedAt;
    }

    public void setSearchedAt(LocalDateTime searchedAt) {
        this.searchedAt = searchedAt;
    }
    // getters and setters
}