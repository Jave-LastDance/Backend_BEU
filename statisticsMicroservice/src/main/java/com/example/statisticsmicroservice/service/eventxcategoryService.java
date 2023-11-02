package com.example.statisticsmicroservice.service;

import com.example.statisticsmicroservice.entity.event;
import com.example.statisticsmicroservice.entity.eventxcategory;
import com.example.statisticsmicroservice.repository.eventxcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class eventxcategoryService {
    @Autowired
    com.example.statisticsmicroservice.repository.eventxcategoryRepository eventxcategoryRepository;

    public List<eventxcategory> getStatistic(String center, List<event> eventsAll){
        updateCategory(center,eventsAll);
        return eventxcategoryRepository.findAllByCenter(center);
    }

    public List<eventxcategory> geteventxcategoryCenter(String center){
        return eventxcategoryRepository.findAllByCenter(center);
    }

    public void updateCategory ( String center, List<event> eventsAll){

        List<eventxcategory> currentStatistics=geteventxcategoryCenter(center);
        Map<String, Integer> quantityCategory = new HashMap<>();

        //Find all the events of that center
        List<event> eventsCenter = new ArrayList<>();
        for (event aux : eventsAll) {
            if (aux.getName_center().equals(center)) {
                eventsCenter.add(aux);
            }
        }

        for (event aux : eventsCenter) {
            String category = aux.getCategory();
            if (quantityCategory.containsKey(category)){}
            quantityCategory.put(category, quantityCategory.getOrDefault(category, 0) + 1);
        }

        for (eventxcategory category: currentStatistics){
            String categoryName = category.getCategory();
            if (quantityCategory.containsKey(categoryName)){
                int currentQuantity = quantityCategory.get(categoryName);
                category.setEvent_count(currentQuantity);
                eventxcategoryRepository.save(category);
                quantityCategory.remove(categoryName);
            }
        }


        for (Map.Entry<String, Integer> entry : quantityCategory.entrySet()) {
            eventxcategory newEvenCat= new eventxcategory();
            newEvenCat.setCategory( entry.getKey());
            newEvenCat.setCenter(center);
            newEvenCat.setEvent_count(entry.getValue());
            eventxcategoryRepository.save(newEvenCat);
        }

    }


}
