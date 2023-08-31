package com.example.eventmicroservice.repository;

import com.example.eventmicroservice.entity.event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface eventRepository extends JpaRepository<event,Integer> {


    @Query(value = "SELECT * FROM event WHERE id_event=?1", nativeQuery = true)
    event findByid_event(Integer id_event);

    @Query(value = "SELECT * FROM event WHERE name LIKE %?1%", nativeQuery = true)
    List<event> findByNameIsLike(String name);

    @Query(value = "SELECT * FROM event WHERE Centerid_unity=?1 AND state=?2", nativeQuery = true)
    List<event> findByCenterIdAndStatus(Integer idCenter, String status);

    @Query(value = "SELECT * FROM event WHERE description LIKE %?1%", nativeQuery = true)
    List<event> findByDescriptionIsLike(String description);

    @Query(value = "SELECT * FROM event WHERE requirements LIKE %?1%", nativeQuery = true)
    List<event> findByRequirementsIsLike(String requirements);

    @Query(value = "SELECT * FROM event WHERE method LIKE %?1%", nativeQuery = true)
    List<event> findByMethodIsLike(String method);

    @Query(value = "SELECT * FROM event WHERE type LIKE %?1%", nativeQuery = true)
    List<event> findByTypeIsLike(String type);

}
