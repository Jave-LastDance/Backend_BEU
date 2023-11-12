package com.beu.apigateway.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@Data
@Entity
@Table(name = "notification")
public class Notification {
    // œColumn
    @Id
    private Integer notification_id;
    private Integer user_id;
    private Integer event_id;
    private String title;
    private String body;
    private String url_notification;
    private String image;
    private Date timeStamp;
    
}
