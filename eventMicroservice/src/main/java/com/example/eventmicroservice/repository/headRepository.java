package com.example.eventmicroservice.repository;

import com.example.eventmicroservice.entity.head;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface headRepository extends JpaRepository<head,Integer> {
    @Query(value = "SELECT * FROM head WHERE id_user=?1", nativeQuery = true)
    List<head> getByIdUser(Integer idUser);

}
