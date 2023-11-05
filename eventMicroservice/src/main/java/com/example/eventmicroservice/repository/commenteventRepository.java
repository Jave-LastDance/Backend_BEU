package com.example.eventmicroservice.repository;

import com.example.eventmicroservice.entity.commentevent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface commenteventRepository extends JpaRepository<commentevent,Integer> {


    List<commentevent> findAllByEventId(Integer id);

    @Query(value = "DELETE  FROM commentevent WHERE id_user=?1 AND Eventid_event=?2", nativeQuery = true)
    void deleteByIdUserAndEventId(Integer idUser, Integer idEvent);
}
