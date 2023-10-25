package co.edu.javeriana.beu.notificationmicroservice.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessage {
    
    private String deviceToken;
    private String title;
    private String body;
    private String image;
    private Map<String,String> data;

}
