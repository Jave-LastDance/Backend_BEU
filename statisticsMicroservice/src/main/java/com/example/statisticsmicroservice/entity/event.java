package com.example.statisticsmicroservice.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class event {

    private Integer id;

    private String name;

    private String description;

    private String tags;

    private String public_type;

    private String requirements;

    private Integer duration;

    private String location;

    private Integer capacity;

    private String mode;

    private String state;

    private String category;

    private String topic;

    private Integer cycle;

    private Integer prom_rating;

    private Date date_start;

    private Date date_end;

    private Time time_start;

    private Time time_end;

    private Date date_start_post;

    private Float price;

    private String url_event;

    private String url_poster;

    private String url_photos;

    private String head_email;

    private String name_center;

    private List<ratingevent> rating;

    private List<commentevent> reviews;

    private List<activity> activities;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getName_center() {
        return name_center;
    }

    public String getState() {
        return state;
    }

    public Integer getProm_rating() {
        return prom_rating;
    }

    public Integer getId() {
        return id;
    }

    public String getHead_email() {
        return head_email;
    }
}
