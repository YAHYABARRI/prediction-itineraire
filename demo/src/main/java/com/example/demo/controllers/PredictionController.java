package com.example.demo.controller;

import com.example.demo.dto.PredictionRequest;
import com.example.demo.dto.PredictionResponse;
import com.example.demo.service.PredictionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prediction")
@CrossOrigin("*")
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping
    public PredictionResponse predict(@RequestBody PredictionRequest request) {
        return predictionService.predictDuration(request);
    }
}