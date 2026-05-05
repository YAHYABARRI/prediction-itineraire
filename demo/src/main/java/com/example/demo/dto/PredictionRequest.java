package com.example.demo.dto;

public class PredictionRequest {
    private double start_lat;
    private double start_lon;
    private double end_lat;
    private double end_lon;
    private double distance;
    private double hour;

    public double getStart_lat() {
        return start_lat;
    }

    public void setStart_lat(double start_lat) {
        this.start_lat = start_lat;
    }

    public double getStart_lon() {
        return start_lon;
    }

    public void setStart_lon(double start_lon) {
        this.start_lon = start_lon;
    }

    public double getEnd_lat() {
        return end_lat;
    }

    public void setEnd_lat(double end_lat) {
        this.end_lat = end_lat;
    }

    public double getEnd_lon() {
        return end_lon;
    }

    public void setEnd_lon(double end_lon) {
        this.end_lon = end_lon;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getHour() {
        return hour;
    }

    public void setHour(double hour) {
        this.hour = hour;
    }
}