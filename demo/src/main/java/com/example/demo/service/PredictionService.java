package com.example.demo.service;

import com.example.demo.dto.PredictionRequest;
import com.example.demo.dto.PredictionResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PredictionService {

    private final RestTemplate restTemplate = new RestTemplate();

    public PredictionResponse predictDuration(PredictionRequest request) {
        String url = "http://127.0.0.1:5000/predict";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PredictionRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<PredictionResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                PredictionResponse.class
        );

        return response.getBody();
    }
}