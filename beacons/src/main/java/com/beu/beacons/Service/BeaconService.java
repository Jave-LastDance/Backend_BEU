package com.beu.beacons.Service;

import com.beu.beacons.Entity.Beacon;
import com.beu.beacons.Repository.BeaconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BeaconService {

    private final BeaconRepository beaconRepository;

    @Autowired
    public BeaconService(BeaconRepository beaconRepository) {
        this.beaconRepository = beaconRepository;
    }

    // Método para obtener los vecinos de un beacon por su id
    public String findNeighborsByBeaconId(String beaconId) {
        return beaconRepository.findNeighborsByBeaconId(beaconId);
    }

    // Método para obtener toda la información de un beacon por su id


    public List<Beacon> findById(String id) {
        return beaconRepository.findById(id);
    }

    public List<Beacon> findAllBeacons() {
        return beaconRepository.findAllBeacons();
    }


}
