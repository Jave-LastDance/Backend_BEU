package com.example.statisticsmicroservice.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "stateevents")
public class stateevents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statistics")
    private Integer id_statistics;
    @Column(name = "event_active")
    private Integer event_active;
    @Column(name = "event_cancel")
    private Integer event_cancel;
    @Column(name = "event_draft")
    private Integer event_draft;
    @Column(name = "event_deactivate")
    private Integer event_deactivate;
    @Column(name = "center")
    private String center;

    public stateevents() {
    }

    public Integer getId_statistics() {
        return id_statistics;
    }

    public void setId_statistics(Integer id_statistics) {
        this.id_statistics = id_statistics;
    }

    public Integer getEvent_active() {
        return event_active;
    }

    public void setEvent_active(Integer event_active) {
        this.event_active = event_active;
    }

    public Integer getEvent_cancel() {
        return event_cancel;
    }

    public void setEvent_cancel(Integer event_cancel) {
        this.event_cancel = event_cancel;
    }

    public Integer getEvent_draft() {
        return event_draft;
    }

    public void setEvent_draft(Integer event_draft) {
        this.event_draft = event_draft;
    }

    public Integer getEvent_deactivate() {
        return event_deactivate;
    }

    public void setEvent_deactivate(Integer event_deactivate) {
        this.event_deactivate = event_deactivate;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }
}
