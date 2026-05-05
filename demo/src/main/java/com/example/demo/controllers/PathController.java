package com.example.demo.controllers;

import com.example.demo.dto.PathPointResponse;
import com.example.demo.dto.PathRequest;
import com.example.demo.service.DijkstraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/path")
@CrossOrigin("*")
public class PathController {

    private final DijkstraService dijkstraService;

    public PathController(DijkstraService dijkstraService) {
        this.dijkstraService = dijkstraService;
    }

    @PostMapping("/shortest")
    public List<PathPointResponse> getShortestPath(@RequestBody PathRequest request) {
        return dijkstraService.findShortestPath(request.getDepartId(), request.getArriveeId());
    }
}