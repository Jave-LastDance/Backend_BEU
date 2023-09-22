package com.example.eventmicroservice.controller;

import com.example.eventmicroservice.entity.*;
import com.example.eventmicroservice.service.eventService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eventosPUJ")
public class eventController {

    @Autowired
    eventService eventService;

    @Autowired
    RestTemplate restTemplate;

    //Mock user from account microservice
    private List<user> userSystem=createUsers();

    //Mock beacon from supplier microservice

    private List<beacon> beaconSystem=createBeacon();


    /**
     * This method handles a GET request to retrieve all events available in the system. It returns a response entity containing the list
     * of events fetched from eventService.
     * @return
     */
    @GetMapping("/eventos")
    public ResponseEntity<List<event>> getAllEvents(){
        return new ResponseEntity<>(changeAll(eventService.getAllEvents()), HttpStatus.OK);
    }

    /**
     * This method handles a GET request to retrieve a specific eventDB based on its idEvent. It returns a response entity containing
     * the retrieved eventDB.
     * @param idEvent
     * @return
     */

    @GetMapping("/evento/{idEvent}")
    public ResponseEntity<event> getEvent(@PathVariable Integer idEvent){
        return new ResponseEntity<>(changeOne(eventService.getOneEvent(idEvent)),HttpStatus.OK);
    }

    /**
     * This method handles a GET request to retrieve all events associated with a specific center based on its idCenter. It returns a response entity containing
     * the list of events associated with the center.
     * @param nameCenter
     * @return
     */
    @GetMapping("/evento/centro/{nameCenter}")
    public ResponseEntity<List<event>> getEventPerCenter(@PathVariable String nameCenter){
        Integer auxIdCenter=findCenterId(getCenterId(),nameCenter);
        List<eventDB> auxEventDB =eventService.getAllEventByCenterId(auxIdCenter);
        if(auxEventDB.isEmpty()){
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(changeAll(auxEventDB),HttpStatus.OK);
    }

    /**
     * This method handles a GET request to retrieve all events that match a given keyword in their name, description, requirements, method, or type. It
     * returns a response entity containing the list of matching events.
     * @param keyword
     * @return
     */
    @GetMapping("/evento/palabra/{keyword}")
    public ResponseEntity<List<event>> getEventPerKeyWord(@PathVariable String keyword){
        List<eventDB> auxEventDB =eventService.getAllMatchKeyWord(keyword);
        if(auxEventDB.isEmpty()){
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(changeAll(auxEventDB),HttpStatus.OK);
    }

    /**
     *The method retrieves a list of eventDB objects associated with the specified id_beacon using
     * a service, and if this list is empty, it returns a response with a status code of 400
     * (BAD_REQUEST) and a null body, signifying the absence of events. Conversely, if events are
     * found, it transforms them into a list of event objects and responds with a status code of
     * 200 (OK), indicating successful retrieval.
     * @param id_beacon
     * @return
     */
    @GetMapping("/beacon/{id_beacon}")
    public ResponseEntity<List<event>> getEventPerKeyWord(@PathVariable Integer id_beacon){
        List<eventDB> auxEventDB =eventService.getAllFromBeacon(id_beacon);
        if(auxEventDB.isEmpty()){
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(changeAll(auxEventDB),HttpStatus.OK);
    }


    /**
     * This method handles a GET request to retrieve events with a specific status associated with a particular center. It returns a response entity
     * containing the list of matching events.
     * @param status
     * @param nameCenter
     * @return
     */
    @GetMapping("/evento/estado/centro/{status}/{nameCenter}")
    public ResponseEntity<List<event>> getEventPerKeyPerCenterStatus(@PathVariable String status, @PathVariable String nameCenter){
        Integer idCenter= findCenterId(getCenterId(),nameCenter);
        List<eventDB> auxEventDB =eventService.getAllEVentsPerStatusAndCenter(idCenter,status);
        return  new ResponseEntity<>(changeAll(auxEventDB),HttpStatus.OK);
    }

    /**
     * This method handles a GET request to retrieve events that start on a specific dateStart. It returns a response entity containing the
     * list of events with the specified start date.
     * @param dateStart
     * @return
     */
    @GetMapping("/evento/fecha/{dateStart}")
    public ResponseEntity<List<event>> getEventPerDate(@PathVariable Date dateStart){
        List<eventDB> auxEventDB =eventService.getAllEventsDate(dateStart);
        return  new ResponseEntity<>(changeAll(auxEventDB),HttpStatus.OK);
    }

    /**
     * This method handles a POST request to add a new eventDB. It takes a eventDB object in the request body,
     * calls the saveEvent method from eventService, and returns a response entity indicating whether the
     * eventDB was successfully added.
     * @param newEvent
     * @return
     */
    @PostMapping("/evento")
    public ResponseEntity<String> addEvent(@RequestBody event newEvent){

        boolean operationSuccess=eventService.saveEvent(transformEventToEventDB(newEvent));
        if(!operationSuccess){
            return new ResponseEntity<>("Verifique los datos del evento", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Agregado correctamente", HttpStatus.OK);
    }

    /**
     * This method handles a PUT request to update an existing eventDB. It takes a eventDB object in the request body,
     * calls the updateEvent method from eventService, and returns a response entity indicating whether the eventDB
     * was successfully updated.
     * @param newEvent
     * @return
     */
    @PutMapping("/evento")
    public ResponseEntity<String> updateEvent(@RequestBody event newEvent){
        boolean operationSuccess=eventService.updateEvent(transformEventToEventDB(newEvent));
        if(!operationSuccess){
            return new ResponseEntity<>("Evento incorrecto", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Modificado correctamente", HttpStatus.OK);
    }

    /**
     * This method handles a PUT request to change the status of an eventDB based on its idEvent. It calls the
     * changeState method from eventService and returns a response entity indicating the status change.
     * @param idEvent
     * @returnfindCenterId
     */
    @PutMapping("/evento/estado/{idEvent}")
    public ResponseEntity<String> changeStatusEvent(@PathVariable Integer idEvent){
        eventService.changeState(idEvent);
        return  new ResponseEntity<>("Estado cambiado correctamente",HttpStatus.OK);
    }

    /**
     * This method handles a DELETE request to delete a specific eventDB based on its idEvent. It calls the
     * deleteEvent method from eventService and returns a response entity indicating successful deletion.
     * @param idEvent
     * @return
     */
    @DeleteMapping("/evento/{idEvent}")
    public ResponseEntity<String> deleteEvent(@PathVariable Integer idEvent){
        eventService.deleteEvent(idEvent);
        return  new ResponseEntity<>(" Evento eliminado correctamente",HttpStatus.OK);
    }
    /**
     * This method handles a POST request to update the promrating of an eventDB based on the provided
     * data. It takes a map containing the idEvent and prom (avarage rating) fields, calls the
     * eventSetRating method from eventService to update the eventDB's rating.Is used each time a new rating
     * is add.
     * @param requestData
     */
    @PostMapping("/evento/nuevo/rating")
    public void updateRating(@RequestBody Map<String, Integer> requestData){
        Integer idEvent = requestData.get("idEvent");
        Integer promRating = requestData.get("prom");
        eventService.eventSetRating(idEvent,promRating);
    }

    /**
     * The following methods are used to make the entity that is going to be sent to the front
     * or that is going to be received.
     *
     */

    private eventDB transformEventToEventDB(event eventFront){
        List<center> auxCenter=getCenterId();
        List<head>auxHead=getHeadId();
        List<user> user=userSystem;
        Integer centerId=findCenterId(auxCenter,eventFront.getName_center());
        Integer headId=findHeadId(eventFront.getHead_email(),user,auxHead);

        eventDB eventAux=new eventDB(eventFront.getId(),eventFront.getName(),eventFront.getDescription(),
                eventFront.getTags(),eventFront.getPublic_type(),eventFront.getRequirements(),eventFront.getDuration(),
                eventFront.getLocation(),eventFront.getCapacity(),eventFront.getMode(),eventFront.getState(),
                eventFront.getCategory(),eventFront.getTopic(),eventFront.getCycle(),eventFront.getProm_rating(),
                eventFront.getDate_start(),eventFront.getDate_end(),eventFront.getTime_start(),eventFront.getTime_end(),
                eventFront.getDate_start_post(),eventFront.getPrice(),eventFront.getUrl_event(),eventFront.getUrl_poster(),
                eventFront.getUrl_photos(),headId,centerId);
        eventAux.setId_beacon(getIdBeacon(beaconSystem,Integer.parseInt(eventFront.getLocation())));


        return  eventAux;
    }

    /**
     * This method takes an eventDB object, extracts relevant information, and creates a
     * corresponding event object. It uses several helper methods to find and
     * set values such as center names and head emails.
     * @param eventDBAux
     * @return
     */
    private event transformEventDBToEvent(eventDB eventDBAux){

        List<center> auxCenter = getCenterId();
        List<head> auxHead = getHeadId();
        List<user> users = userSystem;

        event newEvent = new event(
                eventDBAux.getId(),
                eventDBAux.getName(),
                eventDBAux.getDescription(),
                eventDBAux.getTags(),
                eventDBAux.getPublic_type(),
                eventDBAux.getRequirements(),
                eventDBAux.getDuration(),
                eventDBAux.getLocation(),
                eventDBAux.getCapacity(),
                eventDBAux.getMode(),
                eventDBAux.getState(),
                eventDBAux.getCategory(),
                eventDBAux.getTopic(),
                eventDBAux.getCycle(),
                eventDBAux.getProm_rating(),
                eventDBAux.getDate_start(),
                eventDBAux.getDate_end(),
                eventDBAux.getTime_start(),
                eventDBAux.getTime_end(),
                eventDBAux.getDate_start_post(),
                eventDBAux.getPrice(),
                eventDBAux.getUrl_event(),
                eventDBAux.getUrl_poster(),
                eventDBAux.getUrl_photos(),
                findHeadEmail(users, auxHead, eventDBAux.getHeadidHead()),
                findCenterName(auxCenter, eventDBAux.getCenteridUnity()),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        return newEvent;
    }

    /**
     * Given a list of center objects and an event center name, this method finds
     * and returns the corresponding center's ID.
     * @param auxCenter
     * @param idCenter
     * @return
     */
    private String findCenterName(List<center> auxCenter, Integer idCenter){
        String name="";
        for (center aux: auxCenter){
            if(aux.getIdUnity()==idCenter){
                name=aux.getUnityName();
                break;
            }
        }
        return name;
    }

    /**
     *  Given a user email, a list of user objects, and a list of head objects, this method finds and
     *  returns the email of the head associated with that user.
     * @param user
     * @param auxHead
     * @param idHead
     * @return
     */
    private String findHeadEmail( List<user> user, List<head> auxHead, Integer idHead){
        String email="";
        Integer auxIdUser=-1;
        for(head aux: auxHead){
            if(aux.getIdHead()==idHead){
                auxIdUser=aux.getIdUser();
                break;
            }
        }
        for(user aux:user){
            if(auxIdUser==aux.getId()){
                email=aux.getEmail();
                break;
            }
        }
        return email;
    }

    /**
     *  Given a center name, and a list of center objects,
     *  this method finds and returns
     *  the id of the center associated with that name.
     * @param auxCenter
     * @param eventFrontCenterName
     * @return
     */
    private Integer findCenterId(List<center> auxCenter, String eventFrontCenterName){
        Integer returnId=-1;
        for (center aux: auxCenter){
            if(aux.getUnityName().equals(eventFrontCenterName)){
                returnId =aux.getIdUnity();
                break;
            }
        }
        return returnId;
    }

    /**
     * Given a list of users, a email and a list of center head,
     * this method finds and returns
     * the id of the head associated with that email.
     * @param email
     * @param user
     * @param auxHead
     * @return
     */
    private Integer findHeadId( String email, List<user> user, List<head> auxHead){
        Integer returnId=-1;
        for(user aux:user){
         if(aux.getEmail().equals(email)){
             returnId=aux.getId();
             break;
         }
       }
        for(head aux: auxHead){
            if(aux.getIdUser()==returnId){
                returnId=aux.getIdHead();
                break;
            }
        }
        return returnId;
    }

    /**
     *  Given a list of ratingevent objects and an event ID, this method finds
     *  and returns a list of rating events associated with that event.
     * @param allRating
     * @param idEvent
     * @return
     */
    public List<ratingevent> findRatingEvent(List<ratingevent> allRating, Integer idEvent){
        List<ratingevent> ratingeventsAux=new ArrayList<>();
        for(ratingevent aux:allRating){
            if(aux.getEventid_event()==idEvent){
                ratingeventsAux.add(aux);
            }
        }
        return ratingeventsAux;
    }

    /**
     *  Given a list of commentevent objects and an event ID, this method finds and returns a
     *  list of comment events associated with that event.
     * @param allComments
     * @param idEvent
     * @return
     */
    public List<commentevent> findCommentEvent(List<commentevent> allComments, Integer idEvent){
        List<commentevent> commentevent=new ArrayList<>();
        for(commentevent aux:allComments){
            if(aux.getEventId()==idEvent){
                commentevent.add(aux);
            }
        }
        return commentevent;
    }

    /**
     * This method sends an HTTP GET request to a specified URL and retrieves a list of center
     * objects.
     * @return
     */
    @GetMapping("eventos/centros")
    public  List<center> getCenterId() {
        ResponseEntity<List<center>> responseEntity = restTemplate.exchange(
                "http://localhost:8081/eventosPUJ/centrosPUJ/centros",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<center>>() {}
        );

        List<center> centers = responseEntity.getBody();
        return centers;
    }

    /**
     * This method sends an HTTP GET request to a specified URL and retrieves
     * a list of head objects.
     * @return
     */
    @GetMapping("eventos/encargados")
    public  List<head> getHeadId() {
        ResponseEntity<List<head>>responseEntity = restTemplate.exchange(
                "http://localhost:8081/eventosPUJ/encargadoPUJ/encargados",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<head>>() {}
        );

        List<head> heads = responseEntity.getBody();
        return heads;
    }

    /**
     * This method sends an HTTP GET request to a URL with an event ID and retrieves a list
     * of ratingevent objects associated with that event.
     * @param idEvent
     * @return
     */
    @GetMapping("eventos/rating/{idEvent}")
    public List<ratingevent> getRatingEvent(@PathVariable Integer idEvent) {
        ResponseEntity<List<ratingevent>> responseEntity = restTemplate.exchange(
                "http://localhost:8081/eventosPUJ/ratingPUJ/calificaciones",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ratingevent>>() {}
        );

        List<ratingevent> ratingevents = responseEntity.getBody();
        List<ratingevent> ratingeventAux=findRatingEvent(ratingevents,idEvent);


        return ratingeventAux;
    }

    /**
     * This method sends an HTTP GET request to a URL with an event ID and retrieves a list of
     * commentevent objects associated with that event.
     * @param idEvent
     * @return
     */

    @GetMapping("eventos/comentarios/{idEvent}")
    public List<commentevent> getCommentEvent(@PathVariable Integer idEvent) {
        ResponseEntity<List<commentevent>> responseEntity = restTemplate.exchange(
                "http://localhost:8081/eventosPUJ/comentarioPUJ/comentarios",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<commentevent>>() {}
        );

        List<commentevent> commentevent = responseEntity.getBody();

        List<commentevent> commenteventAux=findCommentEvent(commentevent,idEvent);

        return commenteventAux;
    }

    /**
     *  Given a list of eventDB objects, this method transforms each eventDB into an event, retrieves
     *  and sets the associated ratings and comments, and returns a list of event objects.
     * @param eventDBList
     * @return
     */
    public List<event> changeAll(List<eventDB> eventDBList){
        List<event> eventAux = new ArrayList<>();
            for(eventDB aux:eventDBList){
                eventDB eventHelp=aux;
                event newEvent = transformEventDBToEvent(eventHelp);
                newEvent.setRating(getRatingEvent(eventHelp.getId()));
                newEvent.setReviews(getCommentEvent(eventHelp.getId()));
                newEvent.setActivities(getActivities(eventHelp.getId()));
                eventAux.add(newEvent);
            }
        return eventAux;
    }

    /**
     *  Given a single eventDB object, this method transforms the eventDB into an event, retrieves and sets the associated
     *  ratings and comments, and returns a  event object.
     * @param eventDBAux
     * @return
     */
    public event changeOne(eventDB eventDBAux){
        event newEvent = transformEventDBToEvent(eventDBAux);
        newEvent.setRating(getRatingEvent(eventDBAux.getId()));
        newEvent.setReviews(getCommentEvent(eventDBAux.getId()));
        newEvent.setActivities(getActivities(eventDBAux.getId()));
        return newEvent;
    }


    private List<activity> getActivities( Integer idEvent){
        ResponseEntity<List<activity>> responseEntity = restTemplate.exchange(
                "http://localhost:8081/eventosPUJ/actividadesPUJ/actividades/evento/"+idEvent,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<activity>>() {}
        );

        List<activity> auxActivities = responseEntity.getBody();

        return  auxActivities;

    }

    private Integer getIdBeacon(List<beacon> beacons, Integer idLocation){
        List<Integer> neighbours=new ArrayList<>();
        Integer idBeacon=-1;
        for (beacon aux:beacons){
            neighbours=getAllNeighbours(aux.getNeighbours());
            if(neighbours.contains(idLocation)){
                idBeacon=aux.getBuilding();
            }
        }
        return idBeacon;
    }

    private List<Integer> getAllNeighbours(String neighbours){
        String[] auxNeigh = neighbours.split(",");
        List<Integer> allNeighbours= new ArrayList<>();
        for (String aux:auxNeigh){
            Integer num=Integer.parseInt(aux);
            allNeighbours.add(num);
        }
        return allNeighbours;
    }

    private List<user> createUsers(){
        List<user> userSystem=new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("C://Users//marir//Desktop//Ingenieria//DECIMO//ProyectoFinal//eventMicroservice//src//main//resources//user.json");
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONArray jsonArray = new JSONArray(jsonTokener);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                user user = new user();
                user.setId(jsonObject.getInt("id"));
                user.setName(jsonObject.getString("name"));
                user.setSurname(jsonObject.getString("surname"));
                user.setRol(jsonObject.getString("rol"));
                user.setProgram(jsonObject.getString("program"));
                user.setEmail(jsonObject.getString("email"));
                userSystem.add(user);
            }

            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userSystem;
    }

    private List<beacon> createBeacon(){
        List<beacon> beaconSystem=new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("C://Users//marir//Desktop//Ingenieria//DECIMO//ProyectoFinal//REPOSITORIO//backend//Backend_BEU//eventMicroservice//src//main//resources//beacon.json");
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONArray jsonArray = new JSONArray(jsonTokener);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                beacon beaconAux= new beacon();
                beaconAux.setId_beacon(jsonObject.getInt("id_beacon"));
                beaconAux.setBuilding(Integer.parseInt(jsonObject.getString("location")));
                beaconAux.setNeighbours(jsonObject.getString("neighbours"));
                beaconSystem.add(beaconAux);
            }

            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beaconSystem;
    }

}
