package com.example.statisticsmicroservice.service;

import com.example.statisticsmicroservice.entity.event;
import com.example.statisticsmicroservice.entity.eventsheadstatistics;
import com.example.statisticsmicroservice.repository.eventsheadstatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class eventsheadstatisticService {

    @Autowired
    eventsheadstatisticsRepository eventsheadstatisticRepository;

    public List<eventsheadstatistics> getAll(String center, List<event> allEvents){
        updateHeadEventQuantity(center,allEvents);
        List<eventsheadstatistics> headsCenter=new ArrayList<>();
        for(eventsheadstatistics aux:eventsheadstatisticRepository.findAll()){
            if(aux.getCenter().equals(center)){
                headsCenter.add(aux);
            }
        }
        return  headsCenter;
    }

    public void updateHeadEventQuantity(String center, List<event> allEvents) {

        List<event> eventsActive = new ArrayList<>();
        Map<String, Integer> headEventCounts = new HashMap<>();

        for (event aux: allEvents){
            if (aux.getState().equals("Activo")){
                eventsActive.add(aux);
            }
        }

        for (event aux : eventsActive) {
            if (aux.getName_center().equals(center)) {
                String headId = aux.getHead_email();

                headEventCounts.put(headId, headEventCounts.getOrDefault(headId, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : headEventCounts.entrySet()) {
            String headId = entry.getKey();
            Integer eventCount = entry.getValue();

            eventsheadstatistics existingStat = eventsheadstatisticRepository.findByCenterAndHead(center, headId);
            if (existingStat == null) {
                eventsheadstatistics newStat = new eventsheadstatistics();
                newStat.setCenter(center);
                newStat.setId_head(headId);
                newStat.setQuantity(eventCount);
                eventsheadstatisticRepository.save(newStat);
            } else {
                existingStat.setQuantity(eventCount);
                eventsheadstatisticRepository.save(existingStat);
            }
        }


    }
}
