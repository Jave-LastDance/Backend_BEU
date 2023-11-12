package com.beu.apigateway.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "preference")
public class preference {

    @Id
    @Column(name = "id_preference")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_preference;

    @Column(name = "topic")
    private String topic;

    @Column(name = "center_id")
    private String center_id;

    public preference() {
    }

    public preference(Integer id_preference, String topic, String center_id) {
        this.id_preference = id_preference;
        this.topic = topic;
        this.center_id = center_id;
    }

    public Integer getId_preference() {
        return id_preference;
    }

    public void setId_preference(Integer id_preference) {
        this.id_preference = id_preference;
    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCenter_id() {
        return center_id;
    }

    public void setCenter_id(String center_id) {
        this.center_id = center_id;
    }
}
