package com.authentication.authldap.Controller;

import com.authentication.authldap.Entity.Person;
import com.authentication.authldap.ResponseServiceBus;
import com.authentication.authldap.SecurityConfig;
import com.authentication.authldap.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonCotroller {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private PersonService personS;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String cuenta, @RequestParam String pass) {
        // Llama al método consultarCuenta de SecurityConfig
        String respuesta="";
        ResponseServiceBus response = securityConfig.consultarCuenta(cuenta, pass);

        if (response.isValidacion()) {
            List<Person>respuestaL= personS.findPersonByUser(cuenta);
            return ResponseEntity.ok(respuestaL);
        } else {
            respuesta="No estas registrado";
        }
        return ResponseEntity.ok(respuesta);
    }
}
