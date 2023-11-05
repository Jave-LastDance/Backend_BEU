package com.example.eventmicroservice.service;

import com.example.eventmicroservice.entity.manager;
import com.example.eventmicroservice.repository.managerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class managerService {
    @Autowired
    managerRepository managerRepository;

    /**
     * This method retrieves a list of manager objects associated with a specific center ID.
     * It uses the managerRepository to fetch all managers whose centerId matches the provided
     * idCenter. The list of managers is returned.
     * @param idCenter
     * @return
     */
    public List<manager> getAllManagers(Integer idCenter){
        return managerRepository.findAllByCenterId(idCenter);
    }

    /**
     *This method retrieves a single manager object based on the provided user ID. It uses the
     * managerRepository to fetch the manager whose idUser matches the provided idUser. The single
     * manager object is returned.
     * @param idUser
     * @return
     */
    public manager getOneManager(Integer idUser){
        return managerRepository.getByIdUser(idUser);
    }

    /**
     *This method adds a new manager object to the repository if a manager with the same user ID doesn't
     * already exist. It iterates through existing managers and checks if a manager with the same idUser
     * already exists. If the user doesn't exist as a manager, the new manager object is saved to the
     * repository. Returns true if the operation is successful (i.e., the user doesn't exist as a manager),
     * otherwise false
     * @param managerNew
     */
    public boolean addManager(manager managerNew){
        boolean operationSuccess=true;
        for(manager aux: managerRepository.findAll()){
            if(aux.getIdUser()==managerNew.getIdUser()){
                operationSuccess=false;
            }
        }
        if (operationSuccess) {
            managerRepository.save(managerNew);
        }
        return operationSuccess;
    }

    /**
     *This method deletes a manager object based on the provided user ID. It iterates through existing
     * managers, finds the manager with the provided idUser, and deletes that manager from the repository
     * using the managerRepository. Returns true if the operation is successful (i.e., a manager with the
     * provided idUser was found and deleted), otherwise false.
     * @param idUser
     */
    public boolean deleteManager(Integer idUser){

        boolean operationSuccess=false;
        for(manager aux: managerRepository.findAll()){
            if(aux.getIdUser()==idUser){
                managerRepository.deleteByIdUser(idUser);
                operationSuccess=true;
            }
        }

        return operationSuccess;
    }
}
