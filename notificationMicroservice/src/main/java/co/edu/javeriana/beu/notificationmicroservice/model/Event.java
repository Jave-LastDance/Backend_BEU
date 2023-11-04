package co.edu.javeriana.beu.notificationmicroservice.model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

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

}
