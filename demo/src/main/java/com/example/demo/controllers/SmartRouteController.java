package com.example.demo.controllers;

import com.example.demo.dto.RouteRequest;
import com.example.demo.dto.SmartRouteResponse;
import com.example.demo.service.SmartRouteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class SmartRouteController {

    private final SmartRouteService smartRouteService;

    public SmartRouteController(SmartRouteService smartRouteService) {
        this.smartRouteService = smartRouteService;
    }

    @PostMapping("/smart-route")
    public SmartRouteResponse getSmartRoute(@RequestBody RouteRequest request) {
        return smartRouteService.getSmartRoute(request);
    }
}