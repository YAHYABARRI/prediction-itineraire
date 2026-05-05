package com.example.demo.dto;

import java.util.List;

public class SmartRouteResponse {

    private double distance;
    private double osrmDuration;
    private double predictedDurationMinutes;
    private List<CoordinateResponse> coordinates;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getOsrmDuration() {
        return osrmDuration;
    }

    public void setOsrmDuration(double osrmDuration) {
        this.osrmDuration = osrmDuration;
    }

    public double getPredictedDurationMinutes() {
        return predictedDurationMinutes;
    }

    public void setPredictedDurationMinutes(double predictedDurationMinutes) {
        this.predictedDurationMinutes = predictedDurationMinutes;
    }

    public List<CoordinateResponse> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<CoordinateResponse> coordinates) {
        this.coordinates = coordinates;
    }
}