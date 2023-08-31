package com.example.eventmicroservice.controller;

import com.example.eventmicroservice.entity.event;
import com.example.eventmicroservice.service.eventService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eventosPUJ")
public class eventController {

    @Autowired
    eventService eventService;

    /**
     * This method handles a GET request to retrieve all events available in the system. It returns a response entity containing the list
     * of events fetched from eventService.
     * @return
     */
    @GetMapping("/eventos")
    public ResponseEntity<List<event>> getAllEvents(){
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    /**
     * This method handles a GET request to retrieve a specific event based on its idEvent. It returns a response entity containing
     * the retrieved event.
     * @param idEvent
     * @return
     */

    @GetMapping("/evento/{idEvent}")
    public ResponseEntity<event> getEvent(@PathVariable Integer idEvent){
        return new ResponseEntity<>(eventService.getOneEvent(idEvent),HttpStatus.OK);
    }

    /**
     * This method handles a GET request to retrieve all events associated with a specific center based on its idCenter. It returns a response entity containing
     * the list of events associated with the center.
     * @param idCenter
     * @return
     */
    @GetMapping("/evento/centro/{idCenter}")
    public ResponseEntity<List<event>> getEventPerCenter(@PathVariable Integer idCenter){
        List<event> auxEvent=eventService.getAllEventByCenterId(idCenter);
        if(auxEvent.isEmpty()){
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(auxEvent,HttpStatus.OK);
    }

    /**
     * This method handles a GET request to retrieve all events that match a given keyword in their name, description, requirements, method, or type. It
     * returns a response entity containing the list of matching events.
     * @param keyword
     * @return
     */
    @GetMapping("/evento/palabra/{keyword}")
    public ResponseEntity<List<event>> getEventPerKeyWord(@PathVariable String keyword){
        List<event> auxEvent=eventService.getAllMatchKeyWord(keyword);
        if(auxEvent.isEmpty()){
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(auxEvent,HttpStatus.OK);
    }

    /**
     * This method handles a GET request to retrieve events with a specific status associated with a particular center. It returns a response entity
     * containing the list of matching events.
     * @param status
     * @param idCenter
     * @return
     */
    @GetMapping("/evento/estado/centro/{status}/{idCenter}")
    public ResponseEntity<List<event>> getEventPerKeyPerCenterStatus(@PathVariable String status, @PathVariable Integer idCenter){
        List<event> auxEvent=eventService.getAllEVentsPerStatusAndCenter(idCenter,status);
        return  new ResponseEntity<>(auxEvent,HttpStatus.OK);
    }

    /**
     * This method handles a GET request to retrieve events that start on a specific dateStart. It returns a response entity containing the
     * list of events with the specified start date.
     * @param dateStart
     * @return
     */
    @GetMapping("/evento/fecha/{dateStart}")
    public ResponseEntity<List<event>> getEventPerDate(@PathVariable Date dateStart){
        List<event> auxEvent=eventService.getAllEventsDate(dateStart);
        return  new ResponseEntity<>(auxEvent,HttpStatus.OK);
    }

    /**
     * This method handles a POST request to add a new event. It takes a event object in the request body,
     * calls the saveEvent method from eventService, and returns a response entity indicating whether the
     * event was successfully added.
     * @param newEvent
     * @return
     */
    @PostMapping("/evento")
    public ResponseEntity<String> addEvent(@RequestBody event newEvent){
        boolean operationSuccess=eventService.saveEvent(newEvent);
        if(!operationSuccess){
            return new ResponseEntity<>("Verifique los datos del evento", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Agregado correctamente", HttpStatus.OK);
    }

    /**
     * This method handles a PUT request to update an existing event. It takes a event object in the request body,
     * calls the updateEvent method from eventService, and returns a response entity indicating whether the event
     * was successfully updated.
     * @param newEvent
     * @return
     */
    @PutMapping("/evento")
    public ResponseEntity<String> updateEvent(@RequestBody event newEvent){
        boolean operationSuccess=eventService.updateEvent(newEvent);
        if(!operationSuccess){
            return new ResponseEntity<>("Evento incorrecto", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Modificado correctamente", HttpStatus.OK);
    }

    /**
     * This method handles a PUT request to change the status of an event based on its idEvent. It calls the
     * changeState method from eventService and returns a response entity indicating the status change.
     * @param idEvent
     * @return
     */
    @PutMapping("/evento/estado/{idEvent}")
    public ResponseEntity<String> changeStatusEvent(@PathVariable Integer idEvent){
        eventService.changeState(idEvent);
        return  new ResponseEntity<>("Estado cambiado correctamente",HttpStatus.OK);
    }

    /**
     * This method handles a DELETE request to delete a specific event based on its idEvent. It calls the
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
     * This method handles a POST request to update the promrating of an event based on the provided
     * data. It takes a map containing the idEvent and prom (avarage rating) fields, calls the
     * eventSetRating method from eventService to update the event's rating.Is used each time a new rating
     * is add.
     * @param requestData
     */
    @PostMapping("/evento/nuevo/rating")
    public void updateRating(@RequestBody Map<String, Integer> requestData){
        Integer idEvent = requestData.get("idEvent");
        Integer promRating = requestData.get("prom");
        eventService.eventSetRating(idEvent,promRating);
    }


}
