package com.example.eventmicroservice.service;

import com.example.eventmicroservice.entity.event;
import com.example.eventmicroservice.repository.eventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class eventService {
    @Autowired
    eventRepository eventRepository;

    /**
     * This method retrieves all events available in the system. It queries the event repository to
     * fetch a list of all events and returns it.
     * @return
     */
    public List<event> getAllEvents(){
        return eventRepository.findAll();
    }

    /**
     * This method retrieves a specific event based on its idEvent. It queries the event repository
     * using the provided ID and returns the corresponding event.
     * @param idEvent
     * @return
     */
    public event getOneEvent(Integer idEvent){
        return eventRepository.findByid_event(idEvent);
    }

    /**
     * This method retrieves all events that start at a specific startTime. It iterates through the list
     * of events in the repository and filters those with a matching start time.
     * @param startTime
     * @return
     */
    public List<event> getAllEventsDate(Date startTime){
        List<event> eventAux= new ArrayList<>();
        for(event eventList: eventRepository.findAll() ){
            if(eventList.getDateStart().equals(startTime)){
                eventAux.add(eventList);
            }
        }
        return eventAux;
    }

    /**
     * This method retrieves all events associated with a specific center based on its idCenter.
     * It iterates through the list of events and filters those associated with the provided center ID.
     * @param idCenter
     * @return
     */
    public List<event> getAllEventByCenterId(Integer idCenter){
        List<event> eventAux= new ArrayList<>();
        for(event eventList: eventRepository.findAll() ){
            if(eventList.getCenteridUnity()==idCenter){
                eventAux.add(eventList);
            }
        }
        return eventAux;
    }

    /**
     * This method retrieves all events that match a given keyword in their name, description, requirements,
     * method, or type. It combines search results from multiple repository queries.
     * @param keyword
     * @return
     */
    public List<event> getAllMatchKeyWord(String keyword){
        List<event> auxList=new ArrayList<>();
        auxList.addAll(eventRepository.findByNameIsLike(keyword));
        auxList.addAll(eventRepository.findByDescriptionIsLike(keyword));
        auxList.addAll(eventRepository.findByRequirementsIsLike(keyword));
        auxList.addAll(eventRepository.findByMethodIsLike(keyword));
        auxList.addAll(eventRepository.findByTypeIsLike(keyword));
        return auxList;
    }

    /**
     * This method retrieves all events with a specific status associated with a particular
     * center. It queries the repository for events matching both the center ID and status.
     * @param idCenter
     * @param status
     * @return
     */
    public List<event> getAllEVentsPerStatusAndCenter(Integer idCenter, String status){
        return eventRepository.findByCenterIdAndStatus(idCenter,status);
    }

    /**
     * This method saves a new event to the repository if there is no duplicate event with the same
     * name, period, start date, and end date. It checks for duplicates and only saves if the event
     * is unique.
     * @param eventNew
     * @return
     */
    public boolean saveEvent(event eventNew){
        boolean operationSuccess=true;
        for(event aux:eventRepository.findAll()){
            if(aux.getName().equals(eventNew.getName()) &&
                    aux.getPeriod().equals(eventNew.getPeriod()) &&
                        aux.getDateStart().equals(eventNew.getDateStart())&&
                            aux.getDateEnd().equals(eventNew.getDateEnd())
            ){
                operationSuccess=false;
            }
        }
        if(operationSuccess){
        eventRepository.save(eventNew);
        }
        return operationSuccess;
    }

    /**
     * This method toggles the state of an event (enabled/disabled) based on its idEvent.
     * If the event is currently enabled, it's disabled, and vice versa.
     * @param idEvent
     */
    public void changeState(Integer idEvent){
        event eventNew=getOneEvent(idEvent);
        if(eventNew.getState().equals("habilitado")){
            eventNew.setState("deshabilitado");
            eventRepository.save(eventNew);
        }else{
            eventNew.setState("habilitado");
            eventRepository.save(eventNew);
        }
    }

    /**
     * This method updates an existing event with new data provided in the updateEvent object.
     * It searches for the event in the repository by its ID and updates it if found.
     * @param updateEvent
     * @return
     */
    public boolean updateEvent(event updateEvent){
        boolean operationSuccess=false;
        for (event aux: eventRepository.findAll()){
            if(aux.getIdEvent()==updateEvent.getIdEvent()){
                eventRepository.save(updateEvent);
                operationSuccess=true;
            }
        }
        return operationSuccess;
    }

    /**
     * This method deletes a specific event based on its idEvent from the repository
     * @param idEvent
     */
    public void deleteEvent(Integer idEvent){
        eventRepository.deleteById(idEvent);
    }

    /**
     * This method sets the promrating for a specific event based on its idEvent.
     * It searches for the event in the repository and updates its promorating.
     * @param idEvent
     * @param promRating
     */

    public void eventSetRating(Integer idEvent, Integer promRating){
        for(event eventList: eventRepository.findAll() ){
            if(eventList.getIdEvent()==idEvent){
                eventList.setPromRating(promRating);
                eventRepository.save(eventList);
            }
        }
    }
}
