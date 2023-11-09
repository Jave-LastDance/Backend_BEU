package com.example.eventmicroservice.entity;

import jakarta.persistence.Column;

public class beacon {

    private String id;
    private String neighbors;
    private String supplier;

    private String color;

    private int id_building;


    public beacon() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(String neighbors) {
        this.neighbors = neighbors;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId_building() {
        return id_building;
    }

    public void setId_building(int id_building) {
        this.id_building = id_building;
    }
}
