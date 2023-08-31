package com.example.eventmicroservice.service;

import com.example.eventmicroservice.entity.publicType;
import com.example.eventmicroservice.repository.publicTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class publicService {

    @Autowired
    publicTypeRepository publicTypeRepository;

    /**
     *  Method to get All types of public
     * @return list
     */
    public List<publicType> getAllPublicTypes(){
        return publicTypeRepository.findAll();
    }

    /**
     * Method to save a new type of public
     * @param publicTypeNew
     */
    public boolean savePublicType(publicType publicTypeNew){
        boolean operationSuccess=true;
        for(publicType aux:publicTypeRepository.findAll()){
            if(publicTypeNew.getCategory().equals(aux.getCategory())){
                operationSuccess=false;
            }
        }
        if(operationSuccess){
        publicTypeRepository.save(publicTypeNew);}
        return operationSuccess;
    }

    public boolean updateName(publicType publicTypeUpdate){
        boolean operationSuccess=false;
        for(publicType aux:publicTypeRepository.findAll()){
            if(publicTypeUpdate.getIdPublic()==aux.getIdPublic()){
                operationSuccess=true;
            }
        }
        if(operationSuccess){
            publicTypeRepository.save(publicTypeUpdate);}
        return operationSuccess;
    }



}
