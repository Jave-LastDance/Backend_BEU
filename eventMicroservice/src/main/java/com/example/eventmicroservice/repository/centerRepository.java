package com.example.eventmicroservice.repository;

import com.example.eventmicroservice.entity.center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface centerRepository extends JpaRepository<center,Integer> {

}
