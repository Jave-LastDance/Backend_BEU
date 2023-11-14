package com.example.personalizationmicroservice.controller;


import com.example.personalizationmicroservice.entity.preferencexuser;
import com.example.personalizationmicroservice.service.preferenceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personalizacionPUJ/usuario")
public class preferenceUserController {

        @Autowired
        preferenceUserService preferenceUserService;

        @GetMapping("/preferencias/{idUser}")
        public ResponseEntity<List<preferencexuser>> getAllByUser(@PathVariable Integer idUser){
                return new ResponseEntity<>(preferenceUserService.getAllByUser(idUser), HttpStatus.OK);
        }

        @PostMapping("/preferencias")
        public ResponseEntity<String> addPreferences (@RequestBody List<preferencexuser> newPreference){
                preferenceUserService.addPreferencesUser(newPreference);
                return  new ResponseEntity<>("Se agregaron correctamente", HttpStatus.OK);
        }

        @DeleteMapping("/preferencias/{iduser}")
        public ResponseEntity<String> deletePreference(@PathVariable Integer  iduser){
                preferenceUserService.deletePreference(iduser);
                return  new ResponseEntity<>("Se eliminaron correctamente", HttpStatus.OK);
        }


}
