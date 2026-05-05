package com.example.demo.service;

import com.example.demo.dto.PathPointResponse;
import com.example.demo.models.Lieu;
import com.example.demo.repository.LieuRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DijkstraService {

    private final LieuRepository lieuRepository;

    public DijkstraService(LieuRepository lieuRepository) {
        this.lieuRepository = lieuRepository;
    }

    private static class Edge {
        Long target;
        double weight;

        Edge(Long target, double weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public List<PathPointResponse> findShortestPath(Long departId, Long arriveeId) {
        Map<Long, List<Edge>> graph = buildGraph();

        if (!graph.containsKey(departId) || !graph.containsKey(arriveeId)) {
            throw new RuntimeException("Départ ou arrivée introuvable dans le graphe");
        }

        Map<Long, Double> distances = new HashMap<>();
        Map<Long, Long> previous = new HashMap<>();
        PriorityQueue<Long> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        for (Long node : graph.keySet()) {
            distances.put(node, Double.MAX_VALUE);
        }

        distances.put(departId, 0.0);
        queue.add(departId);

        while (!queue.isEmpty()) {
            Long current = queue.poll();

            if (current.equals(arriveeId)) {
                break;
            }

            for (Edge edge : graph.getOrDefault(current, new ArrayList<>())) {
                double newDistance = distances.get(current) + edge.weight;

                if (newDistance < distances.get(edge.target)) {
                    distances.put(edge.target, newDistance);
                    previous.put(edge.target, current);
                    queue.add(edge.target);
                }
            }
        }

        List<Long> pathIds = new ArrayList<>();
        Long step = arriveeId;

        while (step != null) {
            pathIds.add(step);
            step = previous.get(step);
        }

        Collections.reverse(pathIds);

        if (pathIds.isEmpty() || !pathIds.get(0).equals(departId)) {
            throw new RuntimeException("Aucun chemin trouvé");
        }

        List<PathPointResponse> result = new ArrayList<>();
        for (Long id : pathIds) {
            Lieu lieu = lieuRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Lieu introuvable : " + id));

            result.add(new PathPointResponse(lieu.getLatitude(), lieu.getLongitude()));
        }

        return result;
    }

    private Map<Long, List<Edge>> buildGraph() {
        Map<Long, List<Edge>> graph = new HashMap<>();

        addEdge(graph, 1L, 2L, 25.0);
        addEdge(graph, 2L, 3L, 20.0);
        addEdge(graph, 3L, 4L, 15.0);
        addEdge(graph, 1L, 3L, 60.0);
        addEdge(graph, 2L, 4L, 50.0);

        return graph;
    }

    private void addEdge(Map<Long, List<Edge>> graph, Long source, Long target, double weight) {
        graph.computeIfAbsent(source, k -> new ArrayList<>()).add(new Edge(target, weight));
        graph.computeIfAbsent(target, k -> new ArrayList<>()).add(new Edge(source, weight));
    }
}