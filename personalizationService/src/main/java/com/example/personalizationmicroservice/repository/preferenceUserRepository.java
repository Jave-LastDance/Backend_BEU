package com.example.personalizationmicroservice.repository;

import com.example.personalizationmicroservice.entity.preferencexuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface preferenceUserRepository extends JpaRepository<preferencexuser, Integer> {

    @Query(value = "SELECT * FROM preferenceuser WHERE id_user=?1", nativeQuery = true)
    List<preferencexuser> getAllByIdUser(Integer idUser);

    @Query(value = "DELETE FROM preferenceuser WHERE id_user=?1", nativeQuery = true)
    void deleteByIdUser(Integer idUser);

}
