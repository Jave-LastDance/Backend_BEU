package com.example.eventmicroservice.repository;

import com.example.eventmicroservice.entity.publicxevent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface publicXeventRepository extends JpaRepository<publicxevent,Integer> {

    @Query(value = "DELETE FROM publicxevent WHERE Eventid_event=?1 AND Publicid_public=?2", nativeQuery = true)
    void deleteByEventidEventAndPublicidPublic(Integer idEvent, Integer idPublic);
}
