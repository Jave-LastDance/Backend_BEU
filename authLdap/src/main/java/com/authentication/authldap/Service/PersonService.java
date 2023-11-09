package com.authentication.authldap.Service;

import com.authentication.authldap.Entity.Person;
import com.authentication.authldap.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
   private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public  List<Person> findPersonByUser(String user) {
        return personRepository.findByuser(user);
    }

    public void actualizarToken(String username, String nuevoToken) {
        this.personRepository.updateTokenByUsername(nuevoToken, username);
    }

}
