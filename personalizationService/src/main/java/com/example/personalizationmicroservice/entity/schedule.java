package com.example.personalizationmicroservice.entity;


import java.sql.Time;

public class schedule {

    private Integer id_schedule;

    private Integer id_user;

    private Time time_start;

    private Time time_end;

    private String day;

    public schedule() {
    }

    public schedule(Integer id_schedule, Integer id_user, Time time_start, Time time_end, String day) {
        this.id_schedule = id_schedule;
        this.id_user = id_user;
        this.time_start = time_start;
        this.time_end = time_end;
        this.day = day;
    }

    public Integer getId_schedule() {
        return id_schedule;
    }

    public void setId_schedule(Integer id_schedule) {
        this.id_schedule = id_schedule;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
