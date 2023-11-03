package com.beu.beacons.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "beacon")
public class Beacon {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "neighbors", columnDefinition = "JSON")
    private String neighbors;

    @Column(name = "id_building")
    private Long idBuilding;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "id_building", referencedColumnName = "id_building", insertable = false, updatable = false)
    private Building building;

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

    public Long getIdBuilding() {
        return idBuilding;
    }

    public void setIdBuilding(Long idBuilding) {
        this.idBuilding = idBuilding;
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

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
