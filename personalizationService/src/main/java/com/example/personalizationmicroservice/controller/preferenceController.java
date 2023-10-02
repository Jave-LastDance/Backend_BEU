package com.example.personalizationmicroservice.controller;


import com.example.personalizationmicroservice.entity.preference;
import com.example.personalizationmicroservice.service.preferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personalizacionPUJ")
public class preferenceController {

    @Autowired
    preferenceService preferenceService;

    @GetMapping("/preferencias/{nameCenter}")
    public ResponseEntity<List<preference>> getAllPreferences(@PathVariable String nameCenter){
        return new ResponseEntity<>(preferenceService.getALlIdCenter(nameCenter), HttpStatus.OK);
    }

    @GetMapping("/preferencias")
    public ResponseEntity<List<preference>> getAll(){
        return new ResponseEntity<>(preferenceService.getAll(), HttpStatus.OK);
    }




}
