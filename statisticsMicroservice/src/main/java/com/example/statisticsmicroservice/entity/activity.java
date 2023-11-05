package com.example.statisticsmicroservice.entity;

import jakarta.persistence.Column;

import java.sql.Date;
import java.sql.Time;

public class activity {

    private Integer id_activity;
    private  String name;
    private  String description;
    private  String public_type;
    private Date date;
    private Time time_start;
    private  Time time_end;
    private  String url_poster;
    private  String category;
    private  String topic;
    private  String location;
    private  Integer id_event;


}
