package com.example.eventmicroservice.controller;

import com.example.eventmicroservice.entity.ratingevent;
import com.example.eventmicroservice.service.ratingeventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eventosPUJ/ratingPUJ")
public class ratingController {
    @Autowired
    ratingeventService ratingeventService;
    @Autowired
    RestTemplate restTemplate;

    /**
     * Retrieves a list of ratingevent objects based on the provided eventDB ID.
     * It calls the getRatingPerEvent method from the ratingeventService to fetch
     * the ratings for the given eventDB ID. If ratings are found, it responds with
     * a list of ratingevent objects and HTTP status OK. If no ratings are found,
     * it responds with a BAD REQUEST status.
     * @param idEvent
     * @return
     */

    @GetMapping("/evento/{idEvent}")
    public ResponseEntity<List<ratingevent>> getRatingEvent(@PathVariable Integer idEvent){
        List<ratingevent> ratingeventsFound=ratingeventService.getRatingPerEvent(idEvent);
        if(ratingeventsFound.isEmpty()){
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(ratingeventsFound, HttpStatus.OK);
    }


    /**
     *Retrieves a ratingevent object based on the provided user ID. It calls the
     * getRatingPerUser method from the ratingeventService to fetch the rating
     * eventDB for the given user ID and eventDB ID. If a rating eventDB is found, it responds with
     * the ratingevent object and HTTP status OK. If no rating eventDB is found for
     * the user, it responds with a BAD REQUEST status.
     * @param idUser
     * @param  idEvent
     * @return
     */

    @GetMapping("/evento/usuario/{idUser}/{idEvent}")
    public ResponseEntity<ratingevent> getEventPerUser(@PathVariable Integer idUser, @PathVariable Integer idEvent){
       ratingevent aux=ratingeventService.getRatingPerUser(idUser, idEvent);
        if(aux==null){
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(aux, HttpStatus.OK);
    }

    /**
     * Updates the rating of an eventDB. It expects a ratingevent object in the request body.
     * It calls the save method from the ratingeventService to update the rating for the
     * specified eventDB and user. If the operation is successful, it responds with a message
     * indicating the successful modification. If the eventDB doesn't exist, it responds with
     * a BAD REQUEST status. Also updates the promRating of an eventDB each time the rating is modified.
     * @param ratingeventUpdate
     * @return
     */

    @PutMapping("/evento/rating")
    public  ResponseEntity<String> updateRatingEvent(@RequestBody ratingevent ratingeventUpdate){
        boolean operationSuccess=ratingeventService.save(ratingeventUpdate.getEventid_event(),ratingeventUpdate.getIdUser(), ratingeventUpdate.getGrade());
        if(!operationSuccess){
            return  new ResponseEntity<>("El evento no existe", HttpStatus.BAD_REQUEST);
        }
        updatePromRating(ratingeventUpdate.getEventid_event(),ratingeventService.promRating(ratingeventUpdate.getEventid_event()));
        return  new ResponseEntity<>("Calificación modificada correctamente", HttpStatus.OK);
    }

    /**
     * Adds a new rating for an eventDB. It expects a ratingevent object in the request body.
     * It calls the newRating method from the ratingeventService to add a new rating for the
     * provided eventDB and user. If the operation is successful, it responds with a message
     * indicating the successful addition of the rating. If the user has already rated the eventDB,
     * it responds with a BAD REQUEST status. Also updates the promRating of an eventDB each time
     * the rating is added.
     * @param ratingeventUpdate
     * @return
     */

    @PostMapping("/evento/rating")
    public  ResponseEntity<String> addRatingEvent(@RequestBody ratingevent ratingeventUpdate){
        boolean operationSuccess=ratingeventService.newRating(ratingeventUpdate);
        if(!operationSuccess){
            return  new ResponseEntity<>("Ya calificaste el evento. Puedes modificar la calificacion", HttpStatus.BAD_REQUEST);
        }
        updatePromRating(ratingeventUpdate.getEventid_event(),ratingeventService.promRating(ratingeventUpdate.getEventid_event()));
        return  new ResponseEntity<>("Calificación guardada correctamente", HttpStatus.OK);
    }

    /**
     * This private method is used to update the average rating of an eventDB on a different service.
     * It prepares a request containing the eventDB ID and the calculated average rating (promRating).
     * It sends a POST request to another service (http://localhost:8081/eventosPUJ/evento/nuevo/rating)
     * using RestTemplate.
     * @param idEvent
     * @param promRating
     */
    private void updatePromRating(Integer idEvent, Integer promRating){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Integer> requestBody = new HashMap<>();
        requestBody.put("idEvent", idEvent);
        requestBody.put("prom", promRating);

        HttpEntity<Map<String, Integer>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8081/eventosPUJ/evento/nuevo/rating",
                HttpMethod.POST,
                requestEntity,
                String.class
        );
    }

    /**
     * Returns a list of all the ratings
     * @return
     */
    @GetMapping("/calificaciones")
    public ResponseEntity<List<ratingevent>> getRatingEvent(){
        return new ResponseEntity<>(ratingeventService.getAll(),HttpStatus.OK);
    }








}
