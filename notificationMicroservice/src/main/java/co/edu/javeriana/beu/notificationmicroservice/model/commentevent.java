package co.edu.javeriana.beu.notificationmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class commentevent {

    private  Integer idCommentEvent;
    private Integer idUser;
    private Integer eventId;
    private String comment;

}
