package com.example.personalizationmicroservice.service;

import com.example.personalizationmicroservice.entity.preference;
import com.example.personalizationmicroservice.repository.preferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class preferenceService {

    @Autowired
    preferenceRepository preferenceRepository;

    public List<preference> getAll(){
        return preferenceRepository.findAll();
    }

    public List<preference> getALlIdCenter(String idCenter){return  preferenceRepository.findAllByCenter_id(idCenter);}

    public  void updatePreference(preference preferenceUpdate){
        preferenceRepository.save(preferenceUpdate);
    }

    public void delete(Integer idPreference){
        preferenceRepository.deleteById(idPreference);
    }

}
