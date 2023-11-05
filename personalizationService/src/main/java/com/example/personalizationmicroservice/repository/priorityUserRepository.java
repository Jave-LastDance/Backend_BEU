package com.example.personalizationmicroservice.repository;

import com.example.personalizationmicroservice.entity.priorityxuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface priorityUserRepository extends JpaRepository<priorityxuser, Integer> {

    @Query(value = "SELECT * FROM priorityuser WHERE id_user=?1", nativeQuery = true)
    List<priorityxuser> getAllByUser(Integer idUser);

}
