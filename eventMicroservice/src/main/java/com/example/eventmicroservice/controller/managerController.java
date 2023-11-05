package com.example.eventmicroservice.controller;

import com.example.eventmicroservice.entity.manager;
import com.example.eventmicroservice.service.managerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventosPUJ/managerPUJ")
public class managerController {

    @Autowired
    managerService managerService;

    /**
     * Retrieves a list of manager objects associated with a specific center ID.
     * It calls the getAllManagers method from the managerService to get a list of
     * managers for the given idCenter. If managers are found, it responds with the
     * list of managers and HTTP status OK. If no managers are found, it responds with
     * a BAD REQUEST status.
     * @param idCenter
     * @return
     */
    @GetMapping("/admin/center/{idCenter}")
    public ResponseEntity<List<manager>> getAllManagers(@PathVariable Integer idCenter){
        List<manager> auxManager=managerService.getAllManagers(idCenter);
       if(auxManager.isEmpty()){
           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity<>(auxManager, HttpStatus.OK);
    }

    /**
     * Retrieves a single manager object based on the provided user ID. It calls the getOneManager
     * method from the managerService to fetch the manager for the given idUser. If a manager is found,
     * it responds with the manager object and HTTP status OK. If no manager is found for the user, it
     * responds with a BAD REQUEST status.
     * @param idUser
     * @return
     */
    @GetMapping("/admin/{idUser}")
    public ResponseEntity<manager> getOneManager(@PathVariable Integer idUser){
        manager auxManager=managerService.getOneManager(idUser);
        if(auxManager==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(auxManager, HttpStatus.OK);
    }

    /**
     * Adds a new manager object. Expects a manager object in the request body. It calls the addManager
     * method from the managerService to add a new manager. If the operation is successful, it responds
     * with a message indicating successful assignment. If the user has already been assigned to a center,
     * it responds with a BAD REQUEST status.
     * @param managerNew
     * @return
     */
    @PostMapping("/admin")
    public ResponseEntity<String> addManager(@RequestBody manager managerNew){
        boolean operationSuccess=managerService.addManager(managerNew);
        if(!operationSuccess){
            return  new ResponseEntity<>("Ya ha sido asignado anteriormente a un centro", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Se ha asignado correctamente", HttpStatus.OK);

    }

    /**
     * Deletes a manager object based on the provided user ID. It calls the deleteManager method from
     * the managerService to delete the manager associated with the given idUser. If the operation is
     * successful (i.e., a manager with the provided idUser was deleted), it responds with a message
     * indicating successful deletion. If no user with the given idUser exists, it responds with a BAD
     * REQUEST status.
     * @param idUser
     * @return
     */
    @DeleteMapping("/admin/{idUser}")
    public ResponseEntity<String> deleteManager(@PathVariable Integer idUser){
        boolean operationSuccess=managerService.deleteManager(idUser);
        if(!operationSuccess){
            return  new ResponseEntity<>("No existe usuario", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Se ha eliminado correctamente", HttpStatus.OK);
    }


}
