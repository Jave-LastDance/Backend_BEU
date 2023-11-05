package com.example.eventmicroservice.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "ratingevent")
public class ratingevent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rating_event")
    private Integer idRatingXEvent;

    @Column(name = "grade")
    private Integer grade;


    @Column(name = "eventid_event")
    private Integer eventid_event;

    @Column(name = "id_user")
    private Integer idUser;

    public ratingevent() {
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdRatingXEvent() {
        return idRatingXEvent;
    }

    public Integer getGrade() {
        return grade;
    }

    public Integer getEventid_event() {
        return eventid_event;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void setIdRatingXEvent(Integer idRatingXEvent) {
        this.idRatingXEvent = idRatingXEvent;
    }

    public void setEventid_event(Integer eventid_event) {
        this.eventid_event = eventid_event;
    }
}
