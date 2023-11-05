package com.example.personalizationmicroservice.controller;

import com.example.personalizationmicroservice.entity.priorityxuser;
import com.example.personalizationmicroservice.service.priorityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personalizacionPUJ/prioridad")
public class priorityUserController {

    @Autowired
    priorityUserService priorityUserService;


    @GetMapping("/usuario/{idUser}")
    public ResponseEntity<List<priorityxuser>> getAllByUser(@PathVariable Integer idUser){
        return  new ResponseEntity<>(priorityUserService.getAllUser(idUser), HttpStatus.OK);
    }


    @PutMapping("/usuario")
    public ResponseEntity<String> updatePriority (@RequestBody List<priorityxuser> updatePriority){
        priorityUserService.savePriority(updatePriority);
        return new ResponseEntity<>("Se modificaron correctamente", HttpStatus.OK);
    }

    @PostMapping("/usuario")
    public ResponseEntity<String> newPriority (@RequestBody List<priorityxuser> newPriority){
        priorityUserService.savePriority(newPriority);
        return new ResponseEntity<>("Se agregaron correctamente", HttpStatus.OK);
    }


}
