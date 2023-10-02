package com.example.personalizationmicroservice.service;

import com.example.personalizationmicroservice.entity.priorityxuser;
import com.example.personalizationmicroservice.repository.priorityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class priorityUserService {

    @Autowired
    priorityUserRepository priorityUserRepository;


    public List<priorityxuser> getAll(){

        return priorityUserRepository.findAll();
    }

    public List<priorityxuser> getAllUser(Integer idUser){
        return  priorityUserRepository.getAllByUser(idUser);
    }

    public void savePriority (List<priorityxuser> priorityxuserUpdate){
        for(priorityxuser aux: priorityxuserUpdate) {
            priorityUserRepository.save(aux);
        }
    }








}
