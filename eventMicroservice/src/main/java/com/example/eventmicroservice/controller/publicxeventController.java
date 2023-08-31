package com.example.eventmicroservice.controller;

import com.example.eventmicroservice.entity.publicxevent;
import com.example.eventmicroservice.service.publicXeventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publiceventoPUJ")
public class publicxeventController {

    @Autowired
    publicXeventService publicXeventService ;

    /**
     * This method handles a GET request to retrieve all public events associated with a specific
     * event. It takes the idEvent path variable, retrieves the list of public events using the
     * publicXeventService, and returns a response entity containing the list of public events if
     * they exist. If no public events are found, it returns a BAD REQUEST response.
     * @param idEvent
     * @return
     */
    @GetMapping("/publico/evento/{idEvent}")
    public ResponseEntity<List<publicxevent>> getAllPublicPerEvent(@PathVariable Integer idEvent){
        List<publicxevent> auxPublic=publicXeventService.getAllPublicEvent(idEvent);
        if(auxPublic.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(auxPublic, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles a POST request to add a new public event associated with an event. It
     * takes a publicxevent object in the request body, calls the addNewPublic method from
     * publicXeventService, and returns a response entity indicating whether the public event
     * was successfully added. If an issue occurs, such as an existing event or public identifier,
     * it returns a BAD REQUEST response.
     * @param publicxeventNew
     * @return
     */
    @PostMapping("/publico/evento")
    public ResponseEntity<String> addPublicPerEvent(@RequestBody publicxevent publicxeventNew){
        boolean operationSuccess=publicXeventService.addNewPublic(publicxeventNew);
        if(!operationSuccess){
            return new ResponseEntity<>("Verificar existencia evento y/o publico", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Se ha agregado correctamente", HttpStatus.OK);
    }

    /**
     * This method handles a DELETE request to delete a specific public event associated with an event. It
     * takes idEvent and idPublic as path variables, calls the deletePublic method from publicXeventService,
     * and returns a response entity indicating whether the public event was successfully deleted. If a
     * deletion issue arises, it returns a BAD REQUEST response.
     * @param idEvent
     * @param idPublic
     * @return
     */
    @DeleteMapping("/publico/evento/{idEvent}/{idPublic}")
    public ResponseEntity<String> deletePublicPerEvent(@PathVariable Integer idEvent, @PathVariable Integer idPublic){
        boolean operationSuccess=publicXeventService.deletePublic(idEvent,idPublic);
        if(!operationSuccess){
            return new ResponseEntity<>("Verificar existencia evento o público", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Se ha eliminado correctamente", HttpStatus.OK);

    }
}
