package com.example.eventmicroservice.repository;

import com.example.eventmicroservice.entity.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface activityRepository extends JpaRepository<activity,Integer> {

    @Query(value = "SELECT * FROM activity WHERE id_event=?1", nativeQuery = true)
    List<activity> getByIdEVent(Integer idEvent);


}
