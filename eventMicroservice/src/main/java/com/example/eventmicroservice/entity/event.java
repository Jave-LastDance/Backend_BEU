package com.example.eventmicroservice.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table (name = "event")
public class
event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Integer idEvent;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "place")
    private String place;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "method")
    private String method;

    @Column(name = "state")
    private String state;

    @Column(name = "type")
    private String type;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "period")
    private String period;

    @Column(name = "date_start_post")
    private Date dateStartPost;

    @Column(name = "date_end_post")
    private Date dateEndPost;

    @Column(name = "length")
    private String length;

    @Column(name = "url_event")
    private String urlEvent;

    @Column(name = "url_poster")
    private String urlPoster;



    @Column(name = "Headid_head")
    private Integer HeadidHead;

    @Column(name = "Centerid_unity")
    private Integer CenteridUnity;

    @Column(name = "prom_rating")
    private Integer promRating;

    @Column(name = "url_photos")
    private String url_photos;

    public event() {
    }

    public event(Integer idEvent, String name, String description, String requirements, String place, Integer capacity, String method, String state, String type, Date dateStart, Date dateEnd, String period, Date dateStartPost, Date dateEndPost, String length, String urlEvent, String urlPoster, Integer headidHead, Integer centeridUnity, Integer promRating, String url_photos) {
        this.idEvent = idEvent;
        this.name = name;
        this.description = description;
        this.requirements = requirements;
        this.place = place;
        this.capacity = capacity;
        this.method = method;
        this.state = state;
        this.type = type;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.period = period;
        this.dateStartPost = dateStartPost;
        this.dateEndPost = dateEndPost;
        this.length = length;
        this.urlEvent = urlEvent;
        this.urlPoster = urlPoster;
        HeadidHead = headidHead;
        CenteridUnity = centeridUnity;
        this.promRating = promRating;
        this.url_photos = url_photos;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
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

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getDateStartPost() {
        return dateStartPost;
    }

    public void setDateStartPost(Date dateStartPost) {
        this.dateStartPost = dateStartPost;
    }

    public Date getDateEndPost() {
        return dateEndPost;
    }

    public void setDateEndPost(Date dateEndPost) {
        this.dateEndPost = dateEndPost;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getUrlEvent() {
        return urlEvent;
    }

    public void setUrlEvent(String urlEvent) {
        this.urlEvent = urlEvent;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public Integer getHeadidHead() {
        return HeadidHead;
    }

    public void setHeadidHead(Integer headidHead) {
        HeadidHead = headidHead;
    }

    public Integer getCenteridUnity() {
        return CenteridUnity;
    }

    public void setCenteridUnity(Integer centeridUnity) {
        CenteridUnity = centeridUnity;
    }

    public Integer getPromRating() {
        return promRating;
    }

    public void setPromRating(Integer promRating) {
        this.promRating = promRating;
    }

    public String getUrl_photos() {
        return url_photos;
    }

    public void setUrl_photos(String url_photos) {
        this.url_photos = url_photos;
    }
}
