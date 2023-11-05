package com.example.statisticsmicroservice.service;

import com.example.statisticsmicroservice.entity.event;
import com.example.statisticsmicroservice.entity.stateevents;
import com.example.statisticsmicroservice.repository.stateeventsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class stateventsService {

    @Autowired
    private stateeventsRepository stateeventsRepository;

    @Transactional
    public stateevents getStateCenter(List<event> eventsAll, String center) {
        updateStatistics(eventsAll, center);
        return stateeventsRepository.findByCenter(center);
    }

    public stateevents getStatistic(String center) {
        return stateeventsRepository.findByCenter(center);
    }

    @Transactional
    public void updateStatistics(List<event> eventsAll, String center) {
        stateevents stateevents = new stateevents();
        stateevents.setCenter(center);
        stateevents.setEvent_active(totalOfState("Activo", eventsAll, center));
        stateevents.setEvent_cancel(totalOfState("Cancelado", eventsAll, center));
        stateevents.setEvent_deactivate(totalOfState("Desactivado", eventsAll, center));
        stateevents.setEvent_draft(totalOfState("Borrador", eventsAll, center));

        if (getStatistic(center) != null) {
            stateeventsRepository.updateStateEvents(
                    stateevents.getEvent_active(),
                    stateevents.getEvent_cancel(),
                    stateevents.getEvent_draft(),
                    stateevents.getEvent_deactivate(),
                    center
            );
        } else {
            stateeventsRepository.save(stateevents);
        }
    }

    public int totalOfState(String state, List<event> eventsAll, String center) {
        int cont = 0;
        for (event aux : eventsAll) {
            if (center.equals(aux.getName_center()) && state.equals(aux.getState())) {
                cont++;
            }
        }
        return cont;
    }


}
