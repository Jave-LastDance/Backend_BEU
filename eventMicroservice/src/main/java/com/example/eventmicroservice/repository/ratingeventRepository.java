package com.example.eventmicroservice.repository;

import com.example.eventmicroservice.entity.ratingevent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ratingeventRepository extends JpaRepository<ratingevent,Integer> {

    @Query(value = "SELECT * FROM ratingevent WHERE eventid_event=?1", nativeQuery = true)
    List<ratingevent> findAllByEventId(Integer eventId);



}
