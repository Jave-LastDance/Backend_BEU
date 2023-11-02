package com.example.statisticsmicroservice.repository;


import com.example.statisticsmicroservice.entity.stateevents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface stateeventsRepository extends JpaRepository<stateevents,Integer> {

    @Modifying
    @Query(value = "UPDATE stateevents SET event_active = ?1, event_cancel = ?2, event_draft = ?3, event_deactivate = ?4 WHERE center = ?5", nativeQuery = true)
    void updateStateEvents(int eventActive, int eventCancel, int eventDraft, int eventDeactivate, String center);

    stateevents findByCenter(String name);
}
