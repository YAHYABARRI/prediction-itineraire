package com.example.demo.dto;

public class PredictionResponse {
    private double duration_minutes;
    private double duration_seconds;

    public double getDuration_minutes() {
        return duration_minutes;
    }

    public void setDuration_minutes(double duration_minutes) {
        this.duration_minutes = duration_minutes;
    }

    public double getDuration_seconds() {
        return duration_seconds;
    }

    public void setDuration_seconds(double duration_seconds) {
        this.duration_seconds = duration_seconds;
    }
}