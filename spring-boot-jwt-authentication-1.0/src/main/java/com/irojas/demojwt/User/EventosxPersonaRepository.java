package com.irojas.demojwt.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventosxPersonaRepository extends JpaRepository<EventosxPersona,Long> {

    @Query(value ="SELECT * FROM eventosxpersona b WHERE b.id_persona=?1", nativeQuery = true)
    List<EventosxPersona> findalleventsxperson(Long id);


}
