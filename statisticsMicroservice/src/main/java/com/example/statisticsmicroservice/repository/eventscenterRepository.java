package com.example.statisticsmicroservice.repository;


import com.example.statisticsmicroservice.entity.eventscenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface eventscenterRepository extends JpaRepository<eventscenter,Integer> {

    @Modifying
    @Query(value = "UPDATE eventscenter SET event_count_CJFD = ?1, event_count_CAPS = ?2, event_CGC_count = ?3, event_PSFJ_count = ?4, event_CFICC_count=?5 WHERE id_statistics = ?6", nativeQuery = true)
    void updateCountEvents(int event_count_CJFD , int event_count_CAPS , int event_CGC_count , int event_PSFJ_count, int event_CFICC_count, int id);
}
