package com.example.eventmicroservice.entity;


import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "activity")
public class activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activity")
    private Integer id_activity;

    @Column(name = "name")
    private  String name;

    @Column(name = "description")
    private  String description;

    @Column(name = "public_type")
    private  String public_type;

    @Column(name = "date")
    private Date date;

    @Column(name = "time_start")
    private Time time_start;

    @Column(name = "time_end")
    private  Time time_end;

    @Column(name = "url_poster")
    private  String url_poster;

    @Column(name = "category")
    private  String category;

    @Column(name = "topic")
    private  String topic;

    @Column(name = "location")
    private  String location;

    @Column(name = "id_event")
    private  Integer id_event;


    public activity() {
    }

    public activity(Integer id_activity, String name, String description, String public_type, Date date, Time time_start, Time time_end, String url_poster, String category, String topic, String location, Integer id_event) {
        this.id_activity = id_activity;
        this.name = name;
        this.description = description;
        this.public_type = public_type;
        this.date = date;
        this.time_start = time_start;
        this.time_end = time_end;
        this.url_poster = url_poster;
        this.category = category;
        this.topic = topic;
        this.location = location;
        this.id_event = id_event;
    }

    public Integer getId_activity() {
        return id_activity;
    }

    public void setId_activity(Integer id_activity) {
        this.id_activity = id_activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublic_type() {
        return public_type;
    }

    public void setPublic_type(String public_type) {
        this.public_type = public_type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime_start() {
        return time_start;
    }

    public void setTime_start(Time time_start) {
        this.time_start = time_start;
    }

    public Time getTime_end() {
        return time_end;
    }

    public void setTime_end(Time time_end) {
        this.time_end = time_end;
    }

    public String getUrl_poster() {
        return url_poster;
    }

    public void setUrl_poster(String url_poster) {
        this.url_poster = url_poster;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId_event() {
        return id_event;
    }

    public void setId_event(Integer id_event) {
        this.id_event = id_event;
    }
}
