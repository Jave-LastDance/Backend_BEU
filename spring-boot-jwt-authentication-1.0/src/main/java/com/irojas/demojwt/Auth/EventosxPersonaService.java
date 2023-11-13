package com.irojas.demojwt.Auth;

import com.irojas.demojwt.User.EventosxPersona;
import com.irojas.demojwt.User.EventosxPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventosxPersonaService {

    @Autowired
    private final EventosxPersonaRepository eventosxPersonaRepository;

    public EventosxPersonaService(EventosxPersonaRepository eventosxPersonaRepository) {
        this.eventosxPersonaRepository = eventosxPersonaRepository;
    }

    public ResponseEntity<?> getallById(Long id){
        return ResponseEntity.ok(eventosxPersonaRepository.findalleventsxperson(id));
    }

    public void saveeventxpersona( EventosxPersona eventosxPersona){
         eventosxPersonaRepository.save(eventosxPersona);
    }

    public void deleteeventpersona(EventosxPersona eventosxPersona){
        eventosxPersonaRepository.delete(eventosxPersona);
    }
}
