package com.example.statisticsmicroservice.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "eventxcategory")
public class eventxcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statistics")
    private Integer id_statistics;
    @Column(name = "category")
    private String category;
    @Column(name = "center")
    private String center;
    @Column(name = "event_count")
    private Integer event_count;


    public Integer getId_statistics() {
        return id_statistics;
    }

    public String getCategory() {
        return category;
    }

    public String getCenter() {
        return center;
    }

    public Integer getEvent_count() {
        return event_count;
    }

    public eventxcategory() {
    }

    public void setId_statistics(Integer id_statistics) {
        this.id_statistics = id_statistics;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public void setEvent_count(Integer event_count) {
        this.event_count = event_count;
    }
}
