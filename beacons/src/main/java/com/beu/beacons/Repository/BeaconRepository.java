package com.beu.beacons.Repository;

import com.beu.beacons.Entity.Beacon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BeaconRepository extends JpaRepository<Beacon, Integer> {

    @Query(value ="SELECT b.id_building,b.neighbors FROM Beacon b WHERE b.id", nativeQuery = true)
    String findNeighborsByBeaconId(@Param("beaconId") String beaconId);

    List<Beacon> findById(String id);
}
