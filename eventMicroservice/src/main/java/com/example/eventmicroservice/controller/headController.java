package com.example.eventmicroservice.controller;

import com.example.eventmicroservice.entity.head;
import com.example.eventmicroservice.service.headService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eventosPUJ/encargadoPUJ")
public class headController {


    @Autowired
    headService headService;

    /**
     *Retrieves a list of head objects associated with a specific user ID. It calls the getAllByUser
     * method from the headService to fetch a list of head objects for the given idUser. If heads are
     * found, it responds with the list of head objects and HTTP status OK. If no heads are found, it
     * responds with a BAD REQUEST status.
     * @param idUser
     * @return
     */
    @GetMapping("/encargado/{idUser}")
    public ResponseEntity<List<head>> getHead(@PathVariable Integer idUser){
        List<head> auxHead=headService.getAllByUser(idUser);
        if(auxHead.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(auxHead, HttpStatus.OK);
    }

    /**
     *  Adds a new head object. Expects a head object in the request body. It calls the newHead
     *  method from the headService to add a new head. If the operation is successful, it responds
     *  with a message indicating successful addition. If there's an issue with the provided data,
     *  it responds with a BAD REQUEST status.
     * @param headNew
     * @return
     */
    @PostMapping("/encargado")
    public ResponseEntity<String> newHead(@RequestBody head headNew){
        boolean operationSuccess=headService.newHead(headNew);
        if(!operationSuccess){
            return new ResponseEntity<>("Verifique la información", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Agregado correctamente", HttpStatus.OK);
    }

    /**
     * Updates an existing head object. Expects a head object in the request body with the updated data.
     * It calls the updateHead method from the headService to update the existing head. If the operation
     * is successful, it responds with a message indicating successful modification. If there's an issue
     * with the provided data, it responds with a BAD REQUEST status.
     * @param headUpdate
     * @return
     */
    @PutMapping("/encargado")
    public ResponseEntity<String> updateHead(@RequestBody head headUpdate){
        boolean operationSuccess=headService.updateHead(headUpdate);
        if(!operationSuccess){
            return new ResponseEntity<>("Verifique la información", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Se ha modificado correctamente", HttpStatus.OK);
    }

    @GetMapping("/encargados")
    public ResponseEntity<List<head>> getAll(){
        return new ResponseEntity<>(headService.getAll(),HttpStatus.OK);
    }

}
