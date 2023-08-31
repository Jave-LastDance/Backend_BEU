package com.example.eventmicroservice.repository;

import com.example.eventmicroservice.entity.manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface managerRepository extends JpaRepository<manager,Integer> {
    manager getByIdUser(Integer idUser);

    @Query(value = "DELETE  FROM manager WHERE id_user=?1", nativeQuery = true)
    void deleteByIdUser(Integer idUser);

    @Query(value = "SELECT * FROM manager WHERE Centerid_unity=?1", nativeQuery = true)
    List<manager> findAllByCenterId(Integer centerId);
}
