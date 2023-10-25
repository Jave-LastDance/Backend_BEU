package co.edu.javeriana.beu.notificationmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {
    // œColumn
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notification_id;
    private Integer user_id;
    private Integer event_id;
    private String title;
    private String body;
    private String url_notification;
    private String image;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    
}
