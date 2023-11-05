package com.example.eventmicroservice.repository;

import com.example.eventmicroservice.entity.eventDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface eventRepository extends JpaRepository<eventDB,Integer> {


    @Query(value = "SELECT * FROM event WHERE id_event=?1", nativeQuery = true)
    eventDB findByid_event(Integer id_event);

    @Query(value = "SELECT * FROM event WHERE id_beacon =?1 AND state=?2", nativeQuery = true)
    List<eventDB> findEventBeacon(Integer id_beacon, String state);

    @Query(value = "SELECT * FROM event WHERE name LIKE %?1%", nativeQuery = true)
    List<eventDB> findByNameIsLike(String name);

    @Query(value = "SELECT * FROM event WHERE Centerid_unity=?1 AND state=?2", nativeQuery = true)
    List<eventDB> findByCenterIdAndStatus(Integer idCenter, String status);

    @Query(value = "SELECT * FROM event WHERE description LIKE %?1%", nativeQuery = true)
    List<eventDB> findByDescriptionIsLike(String description);

    @Query(value = "SELECT * FROM event WHERE requirements LIKE %?1%", nativeQuery = true)
    List<eventDB> findByRequirementsIsLike(String requirements);

    @Query(value = "SELECT * FROM event WHERE mode LIKE %?1%", nativeQuery = true)
    List<eventDB> findByModeIsLike(String mode);

    @Query(value = "SELECT * FROM event WHERE category LIKE %?1%", nativeQuery = true)
    List<eventDB> findByCategoryIsLike(String category);

    @Query(value = "SELECT * FROM event WHERE topic LIKE %?1%", nativeQuery = true)
    List<eventDB> findByTopicIsLike(String topic);

    @Query(value = "SELECT * FROM event WHERE tags LIKE %?1%", nativeQuery = true)
    List<eventDB> findByTags(String tags);

    @Query(value = "SELECT * FROM event WHERE name=?1 AND cycle=?2 AND date_start=?3 AND date_end=?4 AND time_start=?5 AND time_end=?6", nativeQuery = true)
    eventDB findMatch(String name, Integer cycle, Date date_start, Date date_end, Time time_start, Time time_end );

}
