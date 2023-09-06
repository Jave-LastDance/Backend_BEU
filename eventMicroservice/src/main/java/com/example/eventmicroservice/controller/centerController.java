package com.example.eventmicroservice.controller;

import com.example.eventmicroservice.entity.center;
import com.example.eventmicroservice.service.centerService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eventosPUJ/centrosPUJ")
public class centerController {

    @Autowired
    centerService centerService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * Retrieves a list of all center objects. It calls the getAllCenter method from
     * the centerService to fetch a list of all centers. It responds with the list of
     * centers and HTTP status OK.
     * @return
     */
    @GetMapping("/centros")
    public ResponseEntity<List<center>> getAllCenter(){
        return  new ResponseEntity<>(centerService.getAllCenter(), HttpStatus.OK);
    }

    /**
     * Retrieves a list of all center names. It calls the getAllNames method from
     * the centerService to fetch a list of all names. It responds with the list of
     *  center names and HTTP status OK.
     * @return
     */
    @GetMapping("/centros/nombres")
    public ResponseEntity<List<String>> getAllCenterNames(){
        return  new ResponseEntity<>(centerService.getAllNames(), HttpStatus.OK);
    }

    /**
     * Adds a new center object. Expects a center object in the request body. It calls
     * the saveCenter method from the centerService to add a new center. If the operation
     * is successful, it responds with a message indicating successful addition. If there's
     * an issue with the provided data (e.g., duplicate name), it responds with a BAD REQUEST
     * status
     * @param newCenter
     * @return
     */
    @PostMapping("/centro")
    public ResponseEntity<String> newCenter(@RequestBody center newCenter){
        boolean operationSucces= centerService.saveCenter(newCenter);
        if(!operationSucces){
            return  new ResponseEntity<>("Verificar Nombre", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Agregado correctamente", HttpStatus.OK);
    }

    /**
     * Updates the name of a center. Expects a center object in the request body with the updated
     * name and ID. It calls the updateName method from the centerService to update the name of the
     * center. If the operation is successful, it responds with a message indicating successful change.
     * If there's an issue with the provided data (e.g., invalid name or ID), it responds with a BAD
     * REQUEST status.
     * @param updateCenter
     * @return
     */
    @PutMapping("/centro")
    public ResponseEntity<String> updateCenterName(@RequestBody center updateCenter){
        boolean operationSuccess= centerService.updateName(updateCenter);
        if(!operationSuccess){
            return  new ResponseEntity<>("Verificar Nombre o Id", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Cambio realizado correctamente", HttpStatus.OK);
    }


}
