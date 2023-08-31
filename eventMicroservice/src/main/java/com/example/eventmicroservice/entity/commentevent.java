package com.example.eventmicroservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "commentevent")
public class commentevent {

    @Id
    @Column(name ="id_comment_event" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer idCommentEvent;
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "Eventid_event")
    private Integer eventId;
    @Column(name = "comment")
    private String comment;

    public commentevent() {
    }

    public Integer getIdCommentEvent() {
        return idCommentEvent;
    }

    public void setIdCommentEvent(Integer idCommentEvent) {
        this.idCommentEvent = idCommentEvent;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
