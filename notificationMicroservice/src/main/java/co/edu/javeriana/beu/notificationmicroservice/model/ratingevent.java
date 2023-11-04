package co.edu.javeriana.beu.notificationmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ratingevent {

    private Integer idRatingXEvent;
    private Integer grade;
    private Integer eventid_event;
    private Integer idUser;

}
