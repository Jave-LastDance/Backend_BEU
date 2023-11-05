package com.example.statisticsmicroservice.service;

import com.example.statisticsmicroservice.entity.event;
import com.example.statisticsmicroservice.entity.eventscenter;
import com.example.statisticsmicroservice.repository.eventscenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class eventscenterService {

    @Autowired
    eventscenterRepository eventscenterRepository;

    /**
     * This method takes a list of events (allEvents) and
     * then calls the updateTopEvents method to update the
     * event values and returns the list of all events in the repository.
     * @param allEvents
     * @return
     */
    public List<eventscenter> getAll(List<event> allEvents) {
        updateTopEvents(allEvents);
        return eventscenterRepository.findAll();
    }

    /**
     * This method is responsible for updating the center's
     * events. It checks if there is already a record of
     * events in the repository. If there isn’t (when
     * eventscenterRepository.count() equals 0), a new
     * one is created, and the updated event values are
     * saved. If a record already exists, the existing
     * record is updated with new event values.
     * @param allEvents
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateTopEvents(List<event> allEvents) {
        List<event> eventsActive = new ArrayList<>();
        for (event aux : allEvents) {
            if (aux.getState().equals("Activo")) {
                eventsActive.add(aux);
            }
        }

        if (eventscenterRepository.count() == 0) {
            eventscenter newEventsCenter = createEventsCenter(eventsActive);
            eventscenterRepository.save(newEventsCenter);
        } else {
            eventscenter existingEventsCenter = eventscenterRepository.findAll().get(0);
            updateEventsCenter(existingEventsCenter, eventsActive);
            eventscenterRepository.save(existingEventsCenter);
        }
    }

    /**
     *  This method is invoked when there is no existing record of events.
     *  It creates a new eventscenter record and uses the
     *  updateEventsCenter method to update the event values.
     * @param eventsActive
     * @return
     */
    private eventscenter createEventsCenter(List<event> eventsActive) {
        eventscenter newEventsCenter = new eventscenter();
        updateEventsCenter(newEventsCenter, eventsActive);
        return newEventsCenter;
    }

    /**
     * This method updates an existing eventscenter record with
     * the provided active event values. It updates the event
     * count for different centers.
     * @param eventscenter
     * @param eventsActive
     */
    private void updateEventsCenter(eventscenter eventscenter, List<event> eventsActive) {
        eventscenter.setEvent_CFICC_count(totalEvents(eventsActive, "CFICC"));
        eventscenter.setEvent_CGC_count(totalEvents(eventsActive, "CGC"));
        eventscenter.setEvent_count_CJFD(totalEvents(eventsActive, "CJFD"));
        eventscenter.setEvent_count_CAPS(totalEvents(eventsActive, "CAPS"));
        eventscenter.setEvent_PSFJ_count(totalEvents(eventsActive, "CPSFJ"));
    }

    /**
     * : It calculates the number of events belonging to a
     * specific center. It iterates through the list of events and counts
     * those that belong to the specified center.
     * @param eventsAll
     * @param center
     * @return
     */
    public int totalEvents(List<event> eventsAll, String center) {
        int count = 0;
        for (event aux : eventsAll) {
            if (center.equals(aux.getName_center())) {
                count++;
            }
        }
        return count;
    }
}
