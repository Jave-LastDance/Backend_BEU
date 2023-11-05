package com.example.statisticsmicroservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class commentevent {

    private  Integer idCommentEvent;
    private Integer idUser;
    private Integer eventId;
    private String comment;

}
