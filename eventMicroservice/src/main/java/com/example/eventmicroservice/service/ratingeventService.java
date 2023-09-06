package com.example.eventmicroservice.service;

import com.example.eventmicroservice.entity.ratingevent;
import com.example.eventmicroservice.repository.ratingeventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ratingeventService {

    @Autowired
    ratingeventRepository ratingeventRepository;

    /**
     * This method retrieves all ratings
     * @return
     */

    public List<ratingevent> getAll(){
        return ratingeventRepository.findAll();
    }


    /**
     * This method retrieves a list of ratingevent objects for a given eventDB ID.
     * It iterates through all ratingevent objects in the repository and collects
     * those that match the provided eventDB ID. The collected list of ratingevent
     * objects is returned.
     * @param idEvent
     * @return
     */
    public List<ratingevent> getRatingPerEvent(Integer idEvent){
        List<ratingevent> ratingeventsAux= new ArrayList<>();
        for(ratingevent aux:ratingeventRepository.findAll()){
            if(aux.getEventid_event()==idEvent){
               ratingeventsAux=ratingeventRepository.findAllByEventId(idEvent);
               break;
            }
        }
        return ratingeventsAux;
    }

    /**
     * This method retrieves a specific ratingevent object based on the provided user
     * ID and eventDB ID. It iterates through all ratingevent objects in the repository
     * and returns the ratingevent object that matches both the user ID and eventDB ID.
     * If no match is found, it returns null.
     * @param idUser
     * @param idEvent
     * @return
     */
    public ratingevent getRatingPerUser(Integer idUser, Integer idEvent){
        ratingevent ratingeventsAux=new ratingevent();
        for(ratingevent aux:ratingeventRepository.findAll()){
            if(aux.getIdUser()==idUser && aux.getEventid_event()==idEvent){
                ratingeventsAux.setGrade(aux.getGrade());
                ratingeventsAux.setIdUser(aux.getIdUser());
                ratingeventsAux.setIdRatingXEvent(aux.getIdRatingXEvent());
                ratingeventsAux.setEventid_event(aux.getEventid_event());
                return ratingeventsAux;
            }
        }
        return null;
    }

    /**
     * This method collects all grades (ratings) for a given eventDB ID. It uses the
     * getRatingPerEvent method to fetch all ratingevent objects for the eventDB and
     * extracts their grades. The collected list of grades is returned.
     * @param idEvent
     * @return
     */
    public List<Integer>getAllGrades(Integer idEvent){
        List<Integer> allgrades=new ArrayList<>();
        for(ratingevent aux:getRatingPerEvent(idEvent)){
            allgrades.add(aux.getGrade());
        }
        return allgrades;
    }

    /**
     * This method calculates the average (promedio) rating for a given eventDB ID.
     * It uses the getAllGrades method to collect all grades, calculates their sum,
     * and then divides the sum by the total number of grades to get the average.
     * The calculated average rating is returned.
     * @param idEvent
     * @return
     */
    public Integer promRating(Integer idEvent){
        Integer sum=0;
        List<Integer> allgrades=getAllGrades(idEvent);
        for (Integer aux: allgrades){
            sum+=aux;
        }
        return (sum/allgrades.size());
    }

    /**
     * This method updates an existing ratingevent object's grade based on the provided
     * eventDB ID and user ID. It iterates through all ratingevent objects and finds the
     * one with matching eventDB and user IDs. It updates the grade and saves the updated
     * object to the repository. Returns true if the operation is successful, otherwise
     * false.
     * @param idEvent
     * @param idUser
     * @param grade
     * @return
     */
    public boolean save(Integer idEvent, Integer idUser, Integer grade){
        boolean operationSuccess=false;
        List<ratingevent> ratingeventsAux= new ArrayList<>();
        for(ratingevent aux:ratingeventRepository.findAll()){
            if(aux.getEventid_event()==idEvent && aux.getIdUser()==idUser){
                aux.setGrade(grade);
                ratingeventRepository.save(aux);
                operationSuccess=true;
            }
        }
        return operationSuccess;
    }

    /**
     * This method adds a new ratingevent object to the repository if the user hasn't
     * rated the eventDB before. It iterates through all ratingevent objects and checks
     * if there's already a rating by the same user. If the user hasn't rated the eventDB,
     * it saves the new ratingevent object. Returns true if the operation is successful
     * (i.e., the user hasn't rated before), otherwise false.
     * @param ratingeventNew
     * @return
     */
    public boolean newRating(ratingevent ratingeventNew){
        boolean operationSuccess=true;
        for(ratingevent aux:ratingeventRepository.findAll()){
            if(aux.getIdUser()==ratingeventNew.getIdUser()){
                operationSuccess=false;
            }
        }
        if(operationSuccess){
            ratingeventRepository.save(ratingeventNew);
        }
      return operationSuccess;
    }


}
