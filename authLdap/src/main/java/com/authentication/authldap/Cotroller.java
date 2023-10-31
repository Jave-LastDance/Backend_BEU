package com.authentication.authldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cotroller {

    @Autowired
    private SecurityConfig securityConfig;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String cuenta, @RequestParam String pass) {
        // Llama al método consultarCuenta de SecurityConfig
        ResponseServiceBus response = securityConfig.consultarCuenta(cuenta, pass);

        if (response.isValidacion()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación");
        }
    }
}
