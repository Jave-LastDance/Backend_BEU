package com.example.personalizationmicroservice.entity;

public class beacon {

    private Integer id_beacon;
    private  Integer building;
    private  String neighbours;

    public beacon() {
    }

    public beacon(Integer id_beacon, Integer building, String neighbours) {
        this.id_beacon = id_beacon;
        this.building = building;
        this.neighbours = neighbours;
    }

    public Integer getId_beacon() {
        return id_beacon;
    }

    public void setId_beacon(Integer id_beacon) {
        this.id_beacon = id_beacon;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    public String getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(String neighbours) {
        this.neighbours = neighbours;
    }
}
