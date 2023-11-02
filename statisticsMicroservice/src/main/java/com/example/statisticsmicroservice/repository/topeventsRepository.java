package com.example.statisticsmicroservice.repository;

import com.example.statisticsmicroservice.entity.topevents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface topeventsRepository extends JpaRepository<topevents,Integer> {

    @Query(value = "SELECT * FROM topevents WHERE id_event=?1 ", nativeQuery = true)
    topevents findByEventId (int event );

    List<topevents> findAllByCenter(String nameCenter);

}
