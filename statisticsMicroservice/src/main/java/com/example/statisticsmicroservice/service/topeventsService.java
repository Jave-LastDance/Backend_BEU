package com.example.statisticsmicroservice.service;

import com.example.statisticsmicroservice.entity.event;
import com.example.statisticsmicroservice.entity.topevents;
import com.example.statisticsmicroservice.repository.topeventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class topeventsService {

    @Autowired
    com.example.statisticsmicroservice.repository.topeventsRepository topeventsRepository;

    public List<topevents> getAllCenter(String centerName, java.util.List<event> allEvents){
        updateEvents(centerName,allEvents);
        List<topevents> topEvents = topeventsRepository.findAllByCenter(centerName);
        topEvents.sort(Comparator.comparing(topevents::getTotal_rating).reversed());
        return topEvents;
    }

    public void updateEvents(String centerName, List<event> allEvents) {
        // Filtrar los eventos que coinciden con el centro y el estado "Desactivado"
        List<event> eventsForCenter = allEvents.stream()
                .filter(aux -> aux.getName_center().equals(centerName) &&
                        aux.getState().equals("Desactivado"))
                .collect(Collectors.toList());

        List<topevents> existingTopEvents = topeventsRepository.findAllByCenter(centerName);

        for (event event : eventsForCenter) {
            topevents existingEvent = existingTopEvents.stream()
                    .filter(existing -> existing.getId_event().equals(event.getId()))
                    .findFirst()
                    .orElse(null);

            if (existingEvent != null) {
                existingEvent.setTotal_rating(event.getProm_rating());
                topeventsRepository.save(existingEvent);
            } else {
                topevents newEvent = new topevents();
                newEvent.setId_event(event.getId());
                newEvent.setCenter(centerName);
                newEvent.setName(event.getName());
                newEvent.setTotal_rating(event.getProm_rating());
                topeventsRepository.save(newEvent);
            }
        }
    }
}



