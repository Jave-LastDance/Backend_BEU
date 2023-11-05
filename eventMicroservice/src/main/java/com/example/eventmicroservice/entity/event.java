package com.example.eventmicroservice.entity;

import jakarta.persistence.Column;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
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

    private  List<activity> activities;

    public List<activity> getActivities() {
        return activities;
    }

    public void setActivities(List<activity> activities) {
        this.activities = activities;
    }

    public event() {
    }

    public event(Integer id, String name, String description, String tags, String public_type, String requirements, Integer duration, String location, Integer capacity, String mode, String state, String category, String topic, Integer cycle, Integer prom_rating, Date date_start, Date date_end, Time time_start, Time time_end, Date date_start_post, Float price, String url_event, String url_poster, String url_photos, String head_email, String name_center, List<ratingevent> rating, List<commentevent> reviews, List<activity> activities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.public_type = public_type;
        this.requirements = requirements;
        this.duration = duration;
        this.location = location;
        this.capacity = capacity;
        this.mode = mode;
        this.state = state;
        this.category = category;
        this.topic = topic;
        this.cycle = cycle;
        this.prom_rating = prom_rating;
        this.date_start = date_start;
        this.date_end = date_end;
        this.time_start = time_start;
        this.time_end = time_end;
        this.date_start_post = date_start_post;
        this.price = price;
        this.url_event = url_event;
        this.url_poster = url_poster;
        this.url_photos = url_photos;
        this.head_email = head_email;
        this.name_center = name_center;
        this.rating = rating;
        this.reviews = reviews;
        this.activities = activities;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setPublic_type(String public_type) {
        this.public_type = public_type;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public void setProm_rating(Integer prom_rating) {
        this.prom_rating = prom_rating;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public void setTime_start(Time time_start) {
        this.time_start = time_start;
    }

    public void setTime_end(Time time_end) {
        this.time_end = time_end;
    }

    public void setDate_start_post(Date date_start_post) {
        this.date_start_post = date_start_post;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setUrl_event(String url_event) {
        this.url_event = url_event;
    }

    public void setUrl_poster(String url_poster) {
        this.url_poster = url_poster;
    }

    public void setUrl_photos(String url_photos) {
        this.url_photos = url_photos;
    }

    public void setHead_email(String head_email) {
        this.head_email = head_email;
    }

    public void setName_center(String name_center) {
        this.name_center = name_center;
    }

    public void setRating(List<ratingevent> rating) {
        this.rating = rating;
    }

    public void setReviews(List<commentevent> reviews) {
        this.reviews = reviews;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTags() {
        return tags;
    }

    public String getPublic_type() {
        return public_type;
    }

    public String getRequirements() {
        return requirements;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getLocation() {
        return location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getMode() {
        return mode;
    }

    public String getState() {
        return state;
    }

    public String getCategory() {
        return category;
    }

    public String getTopic() {
        return topic;
    }

    public Integer getCycle() {
        return cycle;
    }

    public Integer getProm_rating() {
        return prom_rating;
    }

    public Date getDate_start() {
        return date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public Time getTime_start() {
        return time_start;
    }

    public Time getTime_end() {
        return time_end;
    }

    public Date getDate_start_post() {
        return date_start_post;
    }

    public Float getPrice() {
        return price;
    }

    public String getUrl_event() {
        return url_event;
    }

    public String getUrl_poster() {
        return url_poster;
    }

    public String getUrl_photos() {
        return url_photos;
    }

    public String getHead_email() {
        return head_email;
    }

    public String getName_center() {
        return name_center;
    }

    public List<ratingevent> getRating() {
        return rating;
    }

    public List<commentevent> getReviews() {
        return reviews;
    }
}
