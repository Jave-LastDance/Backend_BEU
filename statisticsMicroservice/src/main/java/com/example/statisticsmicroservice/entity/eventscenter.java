package com.example.statisticsmicroservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "eventscenter")
public class eventscenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statistics")
    private Integer id_statistics;
    @Column(name = "event_count_CJFD")
    private Integer event_count_CJFD;
    @Column(name = "event_count_CAPS")
    private Integer event_count_CAPS;
    @Column(name = "event_CGC_count")
    private Integer event_CGC_count;
    @Column(name = "event_PSFJ_count")
    private Integer event_PSFJ_count;
    @Column(name = "event_CFICC_count")
    private Integer event_CFICC_count;

    public eventscenter() {
    }

    public void setEvent_count_CJFD(Integer event_count_CJFD) {
        this.event_count_CJFD = event_count_CJFD;
    }

    public void setEvent_count_CAPS(Integer event_count_CAPS) {
        this.event_count_CAPS = event_count_CAPS;
    }

    public void setEvent_CGC_count(Integer event_CGC_count) {
        this.event_CGC_count = event_CGC_count;
    }

    public void setEvent_PSFJ_count(Integer event_PSFJ_count) {
        this.event_PSFJ_count = event_PSFJ_count;
    }

    public void setEvent_CFICC_count(Integer event_CFICC_count) {
        this.event_CFICC_count = event_CFICC_count;
    }

    public void setId_statistics(Integer id_statistics) {
        this.id_statistics = id_statistics;
    }

    public Integer getId_statistics() {
        return id_statistics;
    }

    public Integer getEvent_count_CJFD() {
        return event_count_CJFD;
    }

    public Integer getEvent_count_CAPS() {
        return event_count_CAPS;
    }

    public Integer getEvent_CGC_count() {
        return event_CGC_count;
    }

    public Integer getEvent_PSFJ_count() {
        return event_PSFJ_count;
    }

    public Integer getEvent_CFICC_count() {
        return event_CFICC_count;
    }
}
