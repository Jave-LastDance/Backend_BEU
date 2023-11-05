package com.example.eventmicroservice.service;

import com.example.eventmicroservice.entity.center;
import com.example.eventmicroservice.repository.centerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class centerService {
    @Autowired
    centerRepository centerRepository;

    /**
     * This method retrieves a list of all center objects from the repository
     * using the centerRepository. It returns the list of center objects.
     * @return
     */
    public List<center> getAllCenter(){

        return centerRepository.findAll();
    }
    /**
     * This method retrieves a list of all center objects from the repository
     * using the centerRepository. It returns the list of the names of each one.
     * @return
     */
    public List<String> getAllNames(){
        List<String> auxNames=new ArrayList<>();
        for(center auxCenter: getAllCenter()){
            auxNames.add(auxCenter.getUnityName());
        }
        return  auxNames;
    }

    /**
     * This method adds a new center object to the repository if the provided name
     * (unityName) is unique. It calls the checkName method to verify the uniqueness
     * of the name. If the name is unique, the new center is saved to the repository
     * using the centerRepository. Returns true if the operation is successful
     * (i.e., the name is unique and the center is saved), otherwise false.
     * @param newCenter
     * @return
     */
    public boolean saveCenter(center newCenter){
        boolean operationSuccess=true;
        if(checkName(newCenter.getUnityName())){
            centerRepository.save(newCenter);
        }else{
            operationSuccess=false;
        }
        return operationSuccess;
    }

    /**
     * This method checks if a given name (nameUnity) is unique among the existing center objects.
     * It iterates through all center objects in the repository and compares the provided name with
     * the existing names. Returns true if the name is unique (i.e., doesn't match any existing names),
     * otherwise false.
     * @param nameUnity
     * @return
     */
    public boolean checkName(String nameUnity){
        boolean operationSuccess=true;
        for(center auxCenter: centerRepository.findAll()){
            if(auxCenter.getUnityName().equals(nameUnity)){
                operationSuccess=false;
            }
        }
        return operationSuccess;
    }

    /**
     * This method updates the name of a center object based on the provided center object (updateCenter).
     * It iterates through all center objects in the repository, finds the center with the matching ID
     * (idUnity), and updates its name if the provided name is unique using the checkName method.
     * Returns true if the operation is successful (i.e., the center's name is updated), otherwise false.
     * @param updateCenter
     * @return
     */
    public boolean updateName(center updateCenter){
        boolean operationSuccess=false;
        for(center auxCenter: centerRepository.findAll()){
            if(updateCenter.getIdUnity()==auxCenter.getIdUnity() && checkName(updateCenter.getUnityName())){
                auxCenter.setUnityName(updateCenter.getUnityName());
                centerRepository.save(auxCenter);
                operationSuccess=true;
            }
        }
        return operationSuccess;
    }

}
