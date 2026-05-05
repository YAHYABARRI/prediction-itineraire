package com.example.demo.service;

import com.example.demo.dto.RouteRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@Service
public class OsrmService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode getRouteData(RouteRequest request) {
        try {
            String url = String.format(
                    Locale.US,
                    "https://router.project-osrm.org/route/v1/driving/%f,%f;%f,%f?overview=full&geometries=geojson",
                    request.getStartLng(),
                    request.getStartLat(),
                    request.getEndLng(),
                    request.getEndLat()
            );

            String response = restTemplate.getForObject(url, String.class);

            return objectMapper.readTree(response);

        } catch (Exception e) {
            throw new RuntimeException("Erreur OSRM : " + e.getMessage());
        }
    }


}