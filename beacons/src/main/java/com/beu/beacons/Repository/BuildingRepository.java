package com.beu.beacons.Repository;

import com.beu.beacons.Entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Integer> {

    List<Building> findAll();
}

