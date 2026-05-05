package com.example.demo.dto;

public class PathRequest {
    private Long departId;
    private Long arriveeId;

    public PathRequest() {
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public Long getArriveeId() {
        return arriveeId;
    }

    public void setArriveeId(Long arriveeId) {
        this.arriveeId = arriveeId;
    }
}