package com.example.statisticsmicroservice.repository;


import com.example.statisticsmicroservice.entity.eventxcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface eventxcategoryRepository extends JpaRepository<eventxcategory,Integer> {

    List<eventxcategory> findAllByCenter(String name);

}
