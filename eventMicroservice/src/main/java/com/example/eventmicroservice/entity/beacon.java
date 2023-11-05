package com.example.eventmicroservice.entity;

public class beacon {

    private Integer id_beacon;
    private Integer location;
    private String neighbours;

    public beacon() {
    }

    public Integer getId_beacon() {
        return id_beacon;
    }

    public void setId_beacon(Integer id_beacon) {
        this.id_beacon = id_beacon;
    }

    public Integer getBuilding() {
        return location;
    }

    public void setBuilding(Integer location) {
        this.location = location;
    }

    public String getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(String neighbours) {
        this.neighbours = neighbours;
    }
}
