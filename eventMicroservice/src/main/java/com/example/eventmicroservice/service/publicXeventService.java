package com.example.eventmicroservice.service;

import com.example.eventmicroservice.entity.publicxevent;
import com.example.eventmicroservice.repository.publicXeventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class publicXeventService {
    @Autowired
    publicXeventRepository publicXeventRepository;

    /**
     * This method retrieves all public events associated with a specific event.
     * It takes the idEvent parameter and iterates through the list of publicxevent
     * objects retrieved from the repository. If a publicxevent object's eventidEvent
     * matches the given idEvent, it's added to the auxPublic list.
     * @param idEvent
     * @return
     */
    public List<publicxevent> getAllPublicEvent(Integer idEvent){
        List<publicxevent> auxPublic= new ArrayList<>();
        for (publicxevent aux: publicXeventRepository.findAll() ){
            if(aux.getEventidEvent()==idEvent){
                auxPublic.add(aux);
            }
        }
        return auxPublic;
    }

    /**
     * This method adds a new public event if no previous public event exists with the same
     * combination of eventidEvent and publicidPublic. It takes a publicxevent object as a
     * parameter. It checks the existing list of publicxevent objects and verifies if there's
     * a duplicate entry based on eventidEvent and publicidPublic. If no duplicate is found,
     * the new public event is saved to the repository, and the method returns true. If a duplicate
     * exists, it returns false.
     * @param publicxeventNew
     * @return
     */
    public boolean addNewPublic(publicxevent publicxeventNew){
        boolean operationSuccess=true;
        for (publicxevent aux: publicXeventRepository.findAll() ){
            if(publicxeventNew.getEventidEvent()==aux.getEventidEvent() && publicxeventNew.getPublicidPublic()==aux.getPublicidPublic()){
                operationSuccess=false;
            }
        }
        if(operationSuccess){
            publicXeventRepository.save(publicxeventNew);
        }
        return operationSuccess;
    }

    /**
     * This method deletes a specific public event associated with an event and identified by its
     * publicidPublic. It takes idEvent and idPublic as parameters. It iterates through the list
     * of publicxevent objects retrieved from the repository. If a matching public event is found
     * based on both eventidEvent and publicidPublic, it's deleted from the repository, and the method
     * returns true. If no matching public event is found, it returns false.
     * @param idEvent
     * @param idPublic
     * @return
     */
    public boolean deletePublic(Integer idEvent, Integer idPublic){
        boolean operationSuccess=false;
        for (publicxevent aux: publicXeventRepository.findAll() ){
            if(aux.getPublicidPublic()==idPublic && aux.getEventidEvent()==idEvent){
                publicXeventRepository.deleteByEventidEventAndPublicidPublic(idEvent,idPublic);
                operationSuccess=true;
            }
        }
     return operationSuccess;
    }




}
