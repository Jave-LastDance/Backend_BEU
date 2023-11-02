package com.example.statisticsmicroservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "topevents")
public class topevents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statistics")
    private Integer id_statistics;

    @Column(name = "id_event")
    private Integer id_event;

    @Column(name = "name")
    private String name;

    @Column(name = "total_rating")
    private Integer total_rating;

    @Column(name = "center")
    private String center;

    public void setId_statistics(Integer id_statistics) {
        this.id_statistics = id_statistics;
    }

    public void setId_event(Integer id_event) {
        this.id_event = id_event;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal_rating(Integer total_rating) {
        this.total_rating = total_rating;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Integer getId_event() {
        return id_event;
    }

    public Integer getId_statistics() {
        return id_statistics;
    }

    public String getName() {
        return name;
    }

    public Integer getTotal_rating() {
        return total_rating;
    }

    public String getCenter() {
        return center;
    }
}
