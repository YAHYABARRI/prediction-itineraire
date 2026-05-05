package com.example.demo.service;

import com.example.demo.dto.*;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmartRouteService {

    private final OsrmService osrmService;
    private final PredictionService predictionService;

    public SmartRouteService(OsrmService osrmService, PredictionService predictionService) {
        this.osrmService = osrmService;
        this.predictionService = predictionService;
    }

    public SmartRouteResponse getSmartRoute(RouteRequest request) {

        JsonNode osrmResponse = osrmService.getRouteData(request);
        JsonNode route = osrmResponse.path("routes").get(0);

        double distance = route.path("distance").asDouble();
        double duration = route.path("duration").asDouble();

        JsonNode coordinatesNode = route.path("geometry").path("coordinates");

        List<CoordinateResponse> coordinates = new ArrayList<>();

        for (JsonNode point : coordinatesNode) {
            double lng = point.get(0).asDouble();
            double lat = point.get(1).asDouble();
            coordinates.add(new CoordinateResponse(lat, lng));
        }

        PredictionRequest pr = new PredictionRequest();
        pr.setStart_lat(request.getStartLat());
        pr.setStart_lon(request.getStartLng());
        pr.setEnd_lat(request.getEndLat());
        pr.setEnd_lon(request.getEndLng());
        pr.setDistance(distance);
        pr.setHour(18);

        PredictionResponse prediction = predictionService.predictDuration(pr);

        SmartRouteResponse response = new SmartRouteResponse();
        response.setDistance(distance);
        response.setOsrmDuration(duration);
        response.setPredictedDurationMinutes(prediction.getDuration_minutes());
        response.setCoordinates(coordinates);

        return response;
    }
}