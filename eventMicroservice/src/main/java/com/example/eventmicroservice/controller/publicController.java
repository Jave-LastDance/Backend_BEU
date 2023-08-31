package com.example.eventmicroservice.controller;

import com.example.eventmicroservice.entity.publicType;
import com.example.eventmicroservice.service.publicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicoPUJ")
public class publicController {

    @Autowired
    publicService publicService;

    @GetMapping("/publico")
    public ResponseEntity<List<publicType>> getALlPublic(){
        return new ResponseEntity<>(publicService.getAllPublicTypes(), HttpStatus.OK);
    }

    @PostMapping("/publico")
    public ResponseEntity<String> addNewPublic(@RequestBody publicType publicTypeNew){
        boolean operationSuccess=publicService.savePublicType(publicTypeNew);
        if(!operationSuccess){
            return new ResponseEntity<>("Ya se encuentra registrado", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Guardado correctamente", HttpStatus.OK);
    }

    @PutMapping("/publico")
    public ResponseEntity<String> updatePublicName(@RequestBody publicType publicTypeUpdate){
        boolean operationSuccess=publicService.updateName(publicTypeUpdate);
        if(!operationSuccess){
            return new ResponseEntity<>("Verifique si esta registrado", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Cambios guardados correctamente", HttpStatus.OK);
    }
}
