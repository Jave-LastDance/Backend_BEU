package com.example.eventmicroservice.controller;

import com.example.eventmicroservice.entity.activity;
import com.example.eventmicroservice.service.activityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventosPUJ/actividadesPUJ")
public class activityController {

    @Autowired
    activityService activityService;

    @GetMapping("/actividades/evento/{idEvent}")
    public ResponseEntity<List<activity>> getAllActivities(@PathVariable Integer idEvent){
        List<activity> auxList= activityService.getAllByEvent(idEvent);
            return new ResponseEntity<>(auxList,HttpStatus.OK);
    }

    @PutMapping("/actividad")
    public ResponseEntity<String> updateActivity(@RequestBody activity updateActivity){
        boolean successOperation=activityService.updateActivity(updateActivity);
        if(successOperation){
            return  new ResponseEntity<>("Se modificó con éxito", HttpStatus.OK);
        }else{
            return  new ResponseEntity<>("Revisar solicitud", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/actividad")
    public ResponseEntity<String> newActivity(@RequestBody activity newActivity){
        boolean successOperation=activityService.addActivity(newActivity);
        if(successOperation){
            return  new ResponseEntity<>("La actividad ya existe, puede modificarla", HttpStatus.BAD_REQUEST);

        }else{
            return  new ResponseEntity<>("Se creó con éxito", HttpStatus.OK);
        }
    }

    @PostMapping("/actividades")
    public ResponseEntity<String> newActivities(@RequestBody List<activity> newActivities){
        boolean auxOperation=true;
        for(activity aux:newActivities){
            auxOperation=activityService.addActivity(aux);
            if(!auxOperation){
                break;
            }
        }
        if(auxOperation){
            return  new ResponseEntity<>("Se crearon correctamente", HttpStatus.OK);

        }else{
            return  new ResponseEntity<>("No se pudieron agregar todas las actividades. Verifique los datos", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/actividad/{idActivity}")
    public ResponseEntity<String> deleteActivity(@PathVariable Integer idActivity){
        activityService.deleteActivity(idActivity);
        return  new ResponseEntity<>("Actividad eliminada correctamente", HttpStatus.OK);
    }





}
