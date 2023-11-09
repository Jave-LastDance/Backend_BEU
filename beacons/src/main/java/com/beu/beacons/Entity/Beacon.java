package com.beu.beacons.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "beacon")
public class Beacon {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "neighbors")
    private String neighbors;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "color")
    private String color;

    @Column(name = "id_building")
    private int id_building;

    // Getters and setters

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
