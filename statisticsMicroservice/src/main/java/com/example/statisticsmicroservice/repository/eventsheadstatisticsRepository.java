package com.example.statisticsmicroservice.repository;

import com.example.statisticsmicroservice.entity.eventsheadstatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface eventsheadstatisticsRepository extends JpaRepository<eventsheadstatistics,Integer> {


    @Query(value = "SELECT * FROM eventsheadstatistics WHERE center=?1  AND id_head=?2", nativeQuery = true)
    eventsheadstatistics findByCenterAndHead(String nameCenter, String idHead);
}
