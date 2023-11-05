package com.example.statisticsmicroservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "eventsheadstatistics")
public class eventsheadstatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statistics")
    private Integer id_statistics;
    @Column(name = "id_head")
    private String id_head;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "center")
    private String center;

    public eventsheadstatistics() {
    }

    public Integer getId_statistics() {
        return id_statistics;
    }

    public String getId_head() {
        return id_head;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getCenter() {
        return center;
    }

    public void setId_statistics(Integer id_statistics) {
        this.id_statistics = id_statistics;
    }

    public void setId_head(String id_head) {
        this.id_head = id_head;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setCenter(String center) {
        this.center = center;
    }
}
