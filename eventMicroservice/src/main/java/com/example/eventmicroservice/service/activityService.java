package com.example.eventmicroservice.service;

import com.example.eventmicroservice.entity.activity;
import com.example.eventmicroservice.repository.activityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class activityService {

    @Autowired
    activityRepository activityRepository;

    //Get all by id_event
    public List<activity> getAllByEvent(Integer idEvent){
        return activityRepository.getByIdEVent(idEvent);
    }

    public boolean updateActivity(activity updateActivity){
        boolean successOperation=false;
        for(activity aux:activityRepository.findAll()){
            if(updateActivity.getId_activity()==aux.getId_activity()){
                successOperation=true;
                activityRepository.save(updateActivity);
                break;
            }
        }
        return successOperation;
    }

    public boolean addActivity(activity newActivity){
        boolean successOperation=true;
        for(activity aux:activityRepository.findAll()){
            if(aux.getName().equals(newActivity.getName())){
                successOperation=false;
                break;
            }
        }

        if(successOperation){
            activityRepository.save(newActivity);
        }
        return successOperation;
    }



    public void deleteActivity(Integer id_activity){
        activityRepository.deleteById(id_activity);
    }


}
