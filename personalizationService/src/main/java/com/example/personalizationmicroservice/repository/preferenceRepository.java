package com.example.personalizationmicroservice.repository;

import com.example.personalizationmicroservice.entity.preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface preferenceRepository extends JpaRepository<preference,Integer> {
    @Query(value = "SELECT * FROM preference WHERE center_id=?1", nativeQuery = true)
    List<preference> findAllByCenter_id(String id);
}
