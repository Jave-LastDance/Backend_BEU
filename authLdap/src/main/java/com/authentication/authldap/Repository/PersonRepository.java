package com.authentication.authldap.Repository;

import com.authentication.authldap.Entity.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query(value ="SELECT * FROM person", nativeQuery = true)
    List<Person> findByuser(String user);


    @Modifying
    @Transactional
    @Query(value="UPDATE Person p SET p.token =?1 WHERE p.usuario =?2", nativeQuery = true)
    void updateTokenByUsername( String newToken,  String username);
}
