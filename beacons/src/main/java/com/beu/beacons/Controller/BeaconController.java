package com.beu.beacons.Controller;

import com.beu.beacons.Entity.Beacon;
import com.beu.beacons.Service.BeaconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beacons")
public class BeaconController {

    private final BeaconService beaconService;

    @Autowired
    public BeaconController(BeaconService beaconService) {
        this.beaconService = beaconService;
    }

    // Endpoint para obtener los vecinos de un beacon por su id
    @GetMapping("/neighbors")
    public ResponseEntity<?> getNeighborsByBeaconId(@RequestParam  String beaconId) {
       String neighbors= beaconService.findNeighborsByBeaconId(beaconId);
       return ResponseEntity.ok(neighbors);
    }

    // Endpoint para obtener toda la información de un beacon por su id
    @GetMapping("/beacon")
    public ResponseEntity<?>getBeaconById(@RequestParam  String beaconId) {
        List<Beacon> beaconfind =beaconService.findById(beaconId);
        return ResponseEntity.ok(beaconfind);
    }

    @GetMapping("/Allbeacons")
   public ResponseEntity<?>getAllBeacon() {
       List<Beacon> beaconsfind =beaconService.findAllBeacons();
       return ResponseEntity.ok(beaconsfind);
   }



}

