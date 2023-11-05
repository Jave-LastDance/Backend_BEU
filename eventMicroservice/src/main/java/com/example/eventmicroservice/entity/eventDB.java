package com.example.eventmicroservice.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table (name = "event")
public class
eventDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "tags")
    private String tags;

    @Column(name = "public_type")
    private String public_type;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "location")
    private String location;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "mode")
    private String mode;

    @Column(name = "state")
    private String state;

    @Column(name = "category")
    private String category;

    @Column(name = "topic")
    private String topic;

    @Column(name = "cycle")
    private Integer cycle;

    @Column(name = "prom_rating")
    private Integer prom_rating;

    @Column(name = "date_start")
    private Date date_start;

    @Column(name = "date_end")
    private Date date_end;

    @Column(name = "time_start")
    private Time time_start;

    @Column(name = "time_end")
    private Time time_end;

    @Column(name = "date_start_post")
    private Date date_start_post;

    @Column(name = "price")
    private Float price;

    @Column(name = "url_event")
    private String url_event;

    @Column(name = "url_poster")
    private String url_poster;

    @Column(name = "url_photos")
    private String url_photos;

    @Column(name = "Headid_head")
    private Integer HeadidHead;

    @Column(name = "Centerid_unity")
    private Integer CenteridUnity;

    @Column(name = "id_beacon")
    private Integer id_beacon;



    public eventDB() {
    }

    public eventDB(Integer id, String name, String description, String tags, String public_type, String requirements, Integer duration, String location, Integer capacity, String mode, String state, String category, String topic, Integer cycle, Integer prom_rating, Date date_start, Date date_end, Time time_start, Time time_end, Date date_start_post, Float price, String url_event, String url_poster, String url_photos, Integer headidHead, Integer centeridUnity) {
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
        HeadidHead = headidHead;
        CenteridUnity = centeridUnity;
    }

    public Integer getId_beacon() {
        return id_beacon;
    }

    public void setId_beacon(Integer id_beacon) {
        this.id_beacon = id_beacon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id_event) {
        this.id = id_event;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPublic_type() {
        return public_type;
    }

    public void setPublic_type(String public_type) {
        this.public_type = public_type;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getProm_rating() {
        return prom_rating;
    }

    public void setProm_rating(Integer prom_rating) {
        this.prom_rating = prom_rating;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
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

    public Date getDate_start_post() {
        return date_start_post;
    }

    public void setDate_start_post(Date date_start_post) {
        this.date_start_post = date_start_post;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getUrl_event() {
        return url_event;
    }

    public void setUrl_event(String url_event) {
        this.url_event = url_event;
    }

    public String getUrl_poster() {
        return url_poster;
    }

    public void setUrl_poster(String url_poster) {
        this.url_poster = url_poster;
    }

    public String getUrl_photos() {
        return url_photos;
    }

    public void setUrl_photos(String url_photos) {
        this.url_photos = url_photos;
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

    @Override
    public String toString() {
        return "eventDB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags='" + tags + '\'' +
                ", public_type='" + public_type + '\'' +
                ", requirements='" + requirements + '\'' +
                ", duration=" + duration +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", mode='" + mode + '\'' +
                ", state='" + state + '\'' +
                ", category='" + category + '\'' +
                ", topic='" + topic + '\'' +
                ", cycle=" + cycle +
                ", prom_rating=" + prom_rating +
                ", date_start=" + date_start +
                ", date_end=" + date_end +
                ", time_start=" + time_start +
                ", time_end=" + time_end +
                ", date_start_post=" + date_start_post +
                ", price=" + price +
                ", url_event='" + url_event + '\'' +
                ", url_poster='" + url_poster + '\'' +
                ", url_photos='" + url_photos + '\'' +
                ", HeadidHead=" + HeadidHead +
                ", CenteridUnity=" + CenteridUnity +
                ", id_beacon=" + id_beacon +
                '}';
    }
}
