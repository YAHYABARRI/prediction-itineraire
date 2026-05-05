package com.example.demo.controllers;

import com.example.demo.dto.RouteRequest;
import com.example.demo.service.OsrmService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/route")
@CrossOrigin("*")
public class RouteController {

    private final OsrmService osrmService;

    public RouteController(OsrmService osrmService) {
        this.osrmService = osrmService;
    }

    @PostMapping
    public ResponseEntity<?> getRoute(@RequestBody RouteRequest request) {
        try {
            JsonNode result = osrmService.getRouteData(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}