package com.beu.beacons.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "building")
public class Building {
    @Id
    @Column(name = "id_building")
    private Long idBuilding;

    @Column(name = "name", length = 100, nullable = false, columnDefinition = "VARCHAR(100) COLLATE latin1_swedish_ci")
    private String name;

    // Getters and setters

    public Long getIdBuilding() {
        return idBuilding;
    }

    public void setIdBuilding(Long idBuilding) {
        this.idBuilding = idBuilding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
