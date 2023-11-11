package com.example.personalizationmicroservice.controller;

import com.example.personalizationmicroservice.entity.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/personalizacionPUJ")
public class eventxuserController {


    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/prioridades/{id_user}")
    private List<priorityxuser> getUserPriorities(@PathVariable Integer id_user){
        ResponseEntity<List<priorityxuser>> responseEntity = restTemplate.exchange(
                "http://localhost:8082/personalizacionPUJ/prioridad/usuario/"+id_user,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<priorityxuser>>() {}
        );

        List<priorityxuser> priorityxusers = responseEntity.getBody();
        return priorityxusers;
        }

    @GetMapping("/preferencia/{id_user}")
    private List<preferencexuser> getUserPreferences(@PathVariable Integer id_user){
        ResponseEntity<List<preferencexuser>> responseEntity = restTemplate.exchange(
                "http://localhost:8082/personalizacionPUJ/usuario/preferencias/"+id_user,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<preferencexuser>>() {}
        );

        List<preferencexuser> preferencexuser = responseEntity.getBody();
        return preferencexuser;
    }

    @GetMapping("/eventos/{id_beacon}")
    private List<event> getAllEvents(@PathVariable Integer id_beacon){
        ResponseEntity<List<event>> responseEntity = restTemplate.exchange(
                "http://localhost:8081/eventosPUJ/beacon/"+id_beacon,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<event>>() {}
        );

        List<event> event = responseEntity.getBody();
        return event;
    }


    @GetMapping("/eventos/usuario/{id_beacon}/{id_user}")
    private List<event> getAllRecommendation(@PathVariable Integer id_beacon,@PathVariable Integer id_user ){
        List<priorityxuser> auxPriorityUser=getUserPriorities(id_user);
        List<event> auxEvent=getAllEvents(id_beacon);
        List<event> recommendationUser=new ArrayList<>();
        user userAux=findUserData(id_user);
        auxPriorityUser.sort(Comparator.comparing(priorityxuser::getImportance).reversed());

        for(priorityxuser aux: auxPriorityUser){
            if(aux.getImportance()==4){
                if(aux.getPriorityrule().equals("Preferencias")){
                    recommendationUser=getAllByPreferences(auxEvent,id_user);
                }else if(aux.getPriorityrule().equals("Programa")){
                    recommendationUser=getAllByProgram(auxEvent,userAux.getPrograma(), userAux.getRol());
                } else if (aux.getPriorityrule().equals("Horario")) {
                    recommendationUser=getAllBySchedule(auxEvent,getUserSchedule(getAllSchedule(),id_user));
                }else{
                    recommendationUser=auxEvent;
                }
            }else if(aux.getImportance()==3 && recommendationUser.size()<=5){
                if(aux.getPriorityrule().equals("Preferencias")){
                    recommendationUser=addOnce(recommendationUser,getAllByPreferences(auxEvent,id_user));
                }else if(aux.getPriorityrule().equals("Programa")){
                    recommendationUser=addOnce(recommendationUser,getAllByProgram(auxEvent,userAux.getPrograma(), userAux.getRol()));
                } else if (aux.getPriorityrule().equals("Horario")) {
                    recommendationUser=addOnce(recommendationUser,getAllBySchedule(auxEvent,getUserSchedule(getAllSchedule(),id_user)));
                }else{
                    recommendationUser=addOnce(recommendationUser,auxEvent);
                }
            } else if (aux.getImportance()==2 && recommendationUser.size()<=5) {
                if(aux.getPriorityrule().equals("Preferencias")){
                    recommendationUser=addOnce(recommendationUser,getAllByPreferences(auxEvent,id_user));
                }else if(aux.getPriorityrule().equals("Programa")){
                    recommendationUser=addOnce(recommendationUser,getAllByProgram(auxEvent,userAux.getPrograma(), userAux.getRol()));
                } else if (aux.getPriorityrule().equals("Horario")) {
                    recommendationUser=addOnce(recommendationUser,getAllBySchedule(auxEvent,getUserSchedule(getAllSchedule(),id_user)));
                }else{
                    recommendationUser=addOnce(recommendationUser,auxEvent);
                }
            }else{
                if(aux.getPriorityrule().equals("Preferencias")){
                    recommendationUser=addOnce(recommendationUser,getAllByPreferences(auxEvent,id_user));
                }else if(aux.getPriorityrule().equals("Programa")){
                    recommendationUser=addOnce(recommendationUser,getAllByProgram(auxEvent,userAux.getPrograma(), userAux.getRol()));
                } else if (aux.getPriorityrule().equals("Horario")) {
                    recommendationUser=addOnce(recommendationUser,getAllBySchedule(auxEvent,getUserSchedule(getAllSchedule(),id_user)));
                }else{
                    recommendationUser=addOnce(recommendationUser,auxEvent);

                }
            }
        }
        return recommendationUser;
    }

    private List<event> getAllBySchedule(List<event> allEvents, List<schedule> userSchedule){
        List<event> matchEvents=new ArrayList<>();
        for(event auxEv: allEvents){
            String eventDay=getDayOfWeek(auxEv.getDate_start());
            List<schedule> scheduleDayUser=getScheduleDayUser(userSchedule,eventDay);
            boolean eventMatch=eventOverlap(scheduleDayUser,auxEv.getTime_start(),auxEv.getTime_end());
            if(!eventMatch){
                matchEvents.add(auxEv);
            }else{
                for (activity auxAct: auxEv.getActivities()){
                   eventDay=getDayOfWeek(auxAct.getDate());
                   scheduleDayUser=getScheduleDayUser(userSchedule,eventDay);
                   eventMatch=eventOverlap(scheduleDayUser,auxEv.getTime_start(),auxEv.getTime_end());
                   if(!eventMatch){
                       matchEvents.add(auxEv);//There is at least one activity at that event that matches with the schedule so adds the event
                   }
                }
            }
        }
        return  matchEvents;
    }

    private String getDayOfWeek(Date eventDate){
        Calendar calendarEvent=Calendar.getInstance();
        calendarEvent.setTime(eventDate);
        int dayOfWeek=calendarEvent.get(Calendar.DAY_OF_WEEK);
        String[] nameDays = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        String day = nameDays[dayOfWeek - 1];
        return day;
    }

    private List<schedule> getScheduleDayUser(List<schedule> allSchedule, String day){
        List<schedule> dailySchedule=new ArrayList<>();
        for(schedule auxSche: allSchedule){
            if(day.equals(auxSche.getDay())){
                dailySchedule.add(auxSche);
            }
        }
        return dailySchedule;
    }

    private boolean eventOverlap(List<schedule> daySchedule, Time event_start, Time event_end){
        boolean successOperation=false;
        for(schedule auxSche: daySchedule){
            if(event_start.before(auxSche.getTime_start())&&event_end.after(auxSche.getTime_end())){
                successOperation= true;
            }
        }
        return successOperation;
    }


    private List<event> getAllByProgram(List<event> allEvents, String program, String rol){
        List<event> matchEvents=new ArrayList<>();
        for(event aux: allEvents){
            if(aux.getPublic_type().contains(program)|| aux.getPublic_type().contains("todos")||
            aux.getPublic_type().contains(rol)){
                matchEvents.add(aux);
            }
            else{
                for(activity auxAct: aux.getActivities()){
                    if(aux.getPublic_type().contains(program)|| aux.getPublic_type().contains("todos")||
                            aux.getPublic_type().contains(rol)){
                        matchEvents.add(aux);
                    }
                }
            }
        }
        return matchEvents;
    }


    private List<preference> preferencesByUser(List<preferencexuser> preferencexuserAll){
        ResponseEntity<List<preference>> responseEntity = restTemplate.exchange(
                "http://localhost:8082/personalizacionPUJ/preferencias",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<preference>>() {}
        );
        List<preference> preference = responseEntity.getBody();
        List<preference> userPreference=new ArrayList<>();

        for(preferencexuser auxUser:preferencexuserAll){
            for (preference auxPref: preference){
                if(auxUser.getPreferenceid_preference()==auxPref.getId_preference()){
                    userPreference.add(auxPref);
                }
            }
        }

        return userPreference;
    }

    private user findUserData(Integer id){
        for(user auxU:getAllUser() ){
            if(auxU.getId()==id){
                return auxU;
            }
        }
        return  null;
    }

    private List<schedule> getUserSchedule(List<schedule> allSchedule, Integer id_user){
            List<schedule> auxSchedule=new ArrayList<>();
            for(schedule auxSche: allSchedule){
                if(auxSche.getId_user()==id_user){
                    auxSchedule.add(auxSche);
                }
            }
            return auxSchedule;
    }
    private List<event> getAllByPreferences(List<event> allEvents, Integer id_user){
        List<event> matchEvents=new ArrayList<>();
        List<preference> preferenceUser=preferencesByUser(getUserPreferences(id_user));

        for(preference auxP:preferenceUser){

            for (event auxEV: allEvents){

                if(auxEV.getTopic().toLowerCase().equals(auxP.getTopic().toLowerCase())){
                    matchEvents.add(auxEV);
                }
                else{
                    for(activity auxAct: auxEV.getActivities()){
                        if(auxAct.getTopic().toLowerCase().equals(auxP.getTopic().toLowerCase())){
                            matchEvents.add(auxEV);//If one of the activities of the event matches with  the preference the event is added
                        }
                    }
                }
            }
        }

        return matchEvents;
    }


    private List<schedule> createSchedule(){
        List<schedule> scheduleSystem=new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("src/main/resources/schedule.json");
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONArray jsonArray = new JSONArray(jsonTokener);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                schedule scheduleAux=new schedule();
                scheduleAux.setId_schedule(jsonObject.getInt("id_schedule"));
                scheduleAux.setId_user(Integer.valueOf(jsonObject.optString("id_user")));
                scheduleAux.setTime_start(Time.valueOf(jsonObject.optString("time_start")));
                scheduleAux.setTime_end(Time.valueOf(jsonObject.optString("time_end")));
                scheduleAux.setDay(jsonObject.optString("day"));
                scheduleSystem.add(scheduleAux);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scheduleSystem;
    }

    private List<event> addOnce(List<event> list1,List<event> list2 ){
        List<Integer>idEvents=new ArrayList<>();
        for(event auxEv:list1){
            idEvents.add(auxEv.getId());
        }

        for(event auxEv:list2){
            if(!idEvents.contains(auxEv.getId())){
                list1.add(auxEv);
            }
        }

        return list1;
    }
    @GetMapping("/users")
    private List<user> getAllUser(){
        List<user> userSystem=new ArrayList<>();
        ResponseEntity<List<user>> responseEntity = restTemplate.exchange(
                "http://localhost:8092/auth/allusers",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<user>>() {}
        );
         userSystem= responseEntity.getBody();


        return userSystem;
    }

    private List<schedule> getAllSchedule(){
        List<schedule>scheduleSystem=new ArrayList<>();
        scheduleSystem=createSchedule();
        return scheduleSystem;
    }
}
