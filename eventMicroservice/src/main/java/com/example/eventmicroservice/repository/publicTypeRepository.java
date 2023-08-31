package com.example.eventmicroservice.repository;

import com.example.eventmicroservice.entity.publicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface publicTypeRepository extends JpaRepository<publicType,Integer> {
}
