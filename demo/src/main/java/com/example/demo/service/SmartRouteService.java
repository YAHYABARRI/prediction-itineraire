package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.models.SearchHistory;
import com.example.demo.models.Utilisateur;
import com.example.demo.repository.SearchHistoryRepository;
import com.example.demo.repository.UtilisateurRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SmartRouteService {

    private final OsrmService osrmService;
    private final PredictionService predictionService;
    private final SearchHistoryRepository searchHistoryRepository;
    private final UtilisateurRepository utilisateurRepository;

    public SmartRouteService(OsrmService osrmService, PredictionService predictionService,
                             SearchHistoryRepository searchHistoryRepository, UtilisateurRepository utilisateurRepository) {
        this.osrmService = osrmService;
        this.predictionService = predictionService;
        this.searchHistoryRepository = searchHistoryRepository;
        this.utilisateurRepository = utilisateurRepository;
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
        pr.setHour(request.getHour() != null ? request.getHour() : java.time.LocalTime.now().getHour());

        PredictionResponse prediction = predictionService.predictDuration(pr);

        // Save Search History if userId is provided
        if (request.getUserId() != null) {
            Optional<Utilisateur> userOpt = utilisateurRepository.findById(request.getUserId());
            if (userOpt.isPresent()) {
                SearchHistory history = new SearchHistory();
                history.setUser(userOpt.get());
                history.setDepartureName(request.getDepartureName());
                history.setDepartureLat(request.getStartLat());
                history.setDepartureLon(request.getStartLng());
                history.setDestinationName(request.getDestinationName());
                history.setDestinationLat(request.getEndLat());
                history.setDestinationLon(request.getEndLng());
                history.setDistance(distance);
                history.setOsrmDuration(duration);
                history.setPredictedDuration(prediction.getDuration_minutes());
                searchHistoryRepository.save(history);
            }
        }

        SmartRouteResponse response = new SmartRouteResponse();
        response.setDistance(distance);
        response.setOsrmDuration(duration);
        response.setPredictedDurationMinutes(prediction.getDuration_minutes());
        response.setCoordinates(coordinates);

        return response;
    }
}