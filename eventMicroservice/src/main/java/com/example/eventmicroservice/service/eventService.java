package com.example.eventmicroservice.service;

import com.example.eventmicroservice.entity.eventDB;
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
     * This method retrieves all events available in the system. It queries the eventDB repository to
     * fetch a list of all events and returns it.
     * @return
     */
    public List<eventDB> getAllEvents(){
        return eventRepository.findAll();
    }

    /**
     * This method retrieves a specific eventDB based on its idEvent. It queries the eventDB repository
     * using the provided ID and returns the corresponding eventDB.
     * @param idEvent
     * @return
     */
    public eventDB getOneEvent(Integer idEvent){
        return eventRepository.findByid_event(idEvent);
    }

    /**
     * This method retrieves all events that start at a specific startTime. It iterates through the list
     * of events in the repository and filters those with a matching start time.
     * @param startTime
     * @return
     */
    public List<eventDB> getAllEventsDate(Date startTime){
        List<eventDB> eventDBAuxes = new ArrayList<>();
        for(eventDB eventDBList : eventRepository.findAll() ){
            if(eventDBList.getDate_start().equals(startTime)){
                eventDBAuxes.add(eventDBList);
            }
        }
        return eventDBAuxes;
    }

    /**
     * This method retrieves all events associated with a specific center based on its idCenter.
     * It iterates through the list of events and filters those associated with the provided center ID.
     * @param idCenter
     * @return
     */
    public List<eventDB> getAllEventByCenterId(Integer idCenter){
        List<eventDB> eventDBAuxes = new ArrayList<>();
        for(eventDB eventDBList : eventRepository.findAll() ){
            if(eventDBList.getCenteridUnity()==idCenter){
                eventDBAuxes.add(eventDBList);
            }
        }
        return eventDBAuxes;
    }

    /**
     * This method retrieves all events that match a given keyword in their name, description, requirements,
     * mode, category or topic. It combines search results from multiple repository queries.
     * @param keyword
     * @return
     */
    public List<eventDB> getAllMatchKeyWord(String keyword){
        List<eventDB> auxList=new ArrayList<>();
        auxList.addAll(eventRepository.findByNameIsLike(keyword));
        auxList.addAll(eventRepository.findByDescriptionIsLike(keyword));
        auxList.addAll(eventRepository.findByRequirementsIsLike(keyword));
        auxList.addAll(eventRepository.findByModeIsLike(keyword));
        auxList.addAll(eventRepository.findByCategoryIsLike(keyword));
        auxList.addAll(eventRepository.findByTopicIsLike(keyword));
        auxList.addAll(eventRepository.findByTags(keyword));
        return auxList;
    }

    /**
     * This method  takes an id_beacon as an input parameter and is designed to retrieve
     * a list of eventDB objects associated with that specific id_beacon. It does so by making a
     * call to the eventRepository and invokes the findEventBeacon method, passing in the id_beacon
     * as an argument
     * @param id_beacon
     * @return
     */
    public List<eventDB> getAllFromBeacon(Integer id_beacon){
        return eventRepository.findEventBeacon(id_beacon);
    }


    /**
     * This method retrieves all events with a specific status associated with a particular
     * center. It queries the repository for events matching both the center ID and status.
     * @param idCenter
     * @param status
     * @return
     */
    public List<eventDB> getAllEVentsPerStatusAndCenter(Integer idCenter, String status){
        return eventRepository.findByCenterIdAndStatus(idCenter,status);
    }

    /**
     * This method saves a new eventDB to the repository if there is no duplicate eventDB with the same
     * name, period, start date, and end date. It checks for duplicates and only saves if the eventDB
     * is unique.
     * @param eventDBNew
     * @return
     */
    public boolean saveEvent(eventDB eventDBNew){
        boolean operationSuccess=true;
        if(eventRepository.findMatch(eventDBNew.getName(), eventDBNew.getCycle(), eventDBNew.getDate_start(), eventDBNew.getDate_end(), eventDBNew.getTime_start(), eventDBNew.getTime_end())!=null) {
                operationSuccess=false;
        }
        if(operationSuccess){
        eventRepository.save(eventDBNew);
        }
        return operationSuccess;
    }

    /**
     * This method toggles the state of an eventDB (enabled/disabled) based on its idEvent.
     * If the eventDB is currently enabled, it's disabled, and vice versa.
     * @param idEvent
     */
    public void changeState(Integer idEvent){
        eventDB eventDBNew =getOneEvent(idEvent);
        if(eventDBNew.getState().equals("publicado")){
            eventDBNew.setState("borrador");
            eventRepository.save(eventDBNew);
        }else{
            eventDBNew.setState("publicado");
            eventRepository.save(eventDBNew);
        }
    }

    /**
     * This method updates an existing eventDB with new data provided in the updateEventDB object.
     * It searches for the eventDB in the repository by its ID and updates it if found.
     * @param updateEventDB
     * @return
     */
    public boolean updateEvent(eventDB updateEventDB){
        boolean operationSuccess=false;
        for (eventDB aux: eventRepository.findAll()){
            if(aux.getId()== updateEventDB.getId()){
                eventRepository.save(updateEventDB);
                operationSuccess=true;
            }
        }
        return operationSuccess;
    }

    /**
     * This method deletes a specific eventDB based on its idEvent from the repository
     * @param idEvent
     */
    public void deleteEvent(Integer idEvent){
        eventRepository.deleteById(idEvent);
    }

    /**
     * This method sets the promrating for a specific eventDB based on its idEvent.
     * It searches for the eventDB in the repository and updates its promorating.
     * @param idEvent
     * @param promRating
     */

    public void eventSetRating(Integer idEvent, Integer promRating){
        for(eventDB eventDBList : eventRepository.findAll() ){
            if(eventDBList.getId()==idEvent){
                eventDBList.setProm_rating(promRating);
                eventRepository.save(eventDBList);
            }
        }
    }
}
