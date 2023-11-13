package com.irojas.demojwt.Auth;

import com.irojas.demojwt.User.EventosxPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    private final EventosxPersonaService eventosxPersonaService;

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request)
    {
        RestTemplate restTemplate = new RestTemplate();
        AuthResponse jwtToken = authService.login(request);
        String url = "http://localhost:8092/api/v1/demo"; // Reemplaza 'puerto' con el puerto de tu aplicación

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtToken.getToken()); // Asegúrate de obtener el token correcto
        HttpEntity<String> requestEntity = new HttpEntity<>(request.username, headers);


        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/allusers")
    public ResponseEntity<?> getallusers()
    {
        return ResponseEntity.ok(authService.getallUsers());
    }

    @PostMapping("/saveEvent")
    public ResponseEntity<?> saveEvent(@RequestBody EventosxPersona eventosxPersona)
    {
        eventosxPersonaService.saveeventxpersona(eventosxPersona);
        return ResponseEntity.ok("Guarado exitosamente");
    }

    @GetMapping("/getalleventsxperson")
    public ResponseEntity<?> getalleventsxperson(@RequestParam Long id)
    {
        return ResponseEntity.ok(eventosxPersonaService.getallById(id).getBody());
    }


    @DeleteMapping("/deleteeventxperson")
    public ResponseEntity<?> deleteeventxperson(@RequestBody EventosxPersona eventosxPersona)
    {
        eventosxPersonaService.deleteeventpersona(eventosxPersona);
        return ResponseEntity.ok("eliminado correctamente");
    }

}
