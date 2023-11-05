package com.example.personalizationmicroservice.service;

import com.example.personalizationmicroservice.entity.preferencexuser;
import com.example.personalizationmicroservice.repository.preferenceUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class preferenceUserService {

    @Autowired
    preferenceUserRepository preferenceUserRepository;

    public List<preferencexuser> getAllByUser(Integer idUser){
        return preferenceUserRepository.getAllByIdUser(idUser);
    }


    public void addPreferencesUser(List<preferencexuser> newPreferences){
        for(preferencexuser aux: newPreferences){
            preferenceUserRepository.save(aux);
        }
    }

    public void deletePreference (List<preferencexuser> deletePreferences){
        for(preferencexuser aux: deletePreferences){
        preferenceUserRepository.deleteByIdUserAndIdPreference(aux.getId_user(),aux.getId_preference_user());
        }
    }


}
