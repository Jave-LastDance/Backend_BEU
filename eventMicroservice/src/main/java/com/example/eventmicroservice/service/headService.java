package com.example.eventmicroservice.service;

import com.example.eventmicroservice.entity.head;
import com.example.eventmicroservice.repository.headRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class headService {

    @Autowired
    headRepository headRepository;

    /**
     *This method retrieves a list of head objects associated with a specific user ID. It calls
     * the getByIdUser method from the headRepository to fetch all heads associated with the provided
     * idUser. It returns the list of head objects.
     * @param idUser
     * @return
     */
    public List<head> getAllByUser(Integer idUser){
        return headRepository.getByIdUser(idUser);
    }

    /**
     *This method adds a new head object to the repository if a head with the same user ID and center ID
     * doesn't already exist. It iterates through existing heads and checks if a head with the same idUser
     * and centeridUnity combination exists. If the combination is unique, the new head is saved to the
     * repository using the headRepository. Returns true if the operation is successful (i.e.,
     * the combination is unique and the head is saved), otherwise false.
     * @param headNew
     */
    public boolean newHead (head headNew){
        boolean operationSuccess=true;
        for(head aux:headRepository.findAll()){
            if(headNew.getCenteridUnity()==aux.getCenteridUnity() &&headNew.getIdUser()==aux.getIdUser()){
                operationSuccess=false;
            }
        }
        if(operationSuccess) {
            headRepository.save(headNew);
        }
        return operationSuccess;
    }

    /**
     *This method updates an existing head object's center ID based on the provided headUpdate object. It
     * iterates through existing heads and finds the head with the matching ID (idHead) and user ID. It then
     * updates the center ID of that head with the new center ID from headUpdate and saves the updated head
     * to the repository using the headRepository. Returns true if the operation is successful (i.e., the
     * head's center ID is updated), otherwise false.
     * @param headUpdate
     * @return
     */
    public boolean updateHead(head headUpdate){
        boolean operationSuccess=false;
        for(head aux:headRepository.findAll()){
            if(headUpdate.getIdHead()==aux.getIdHead() && headUpdate.getIdUser()==aux.getIdUser()){
                aux.setCenteridUnity(headUpdate.getCenteridUnity());
                headRepository.save(aux);
                operationSuccess=true;
            }
        }

        return operationSuccess;
    }

}
