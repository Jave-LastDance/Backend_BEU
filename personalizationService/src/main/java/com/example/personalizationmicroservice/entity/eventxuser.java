package com.example.personalizationmicroservice.entity;
import com.example.personalizationmicroservice.entity.event;

import java.util.List;

public class eventxuser {

    private Integer idUser;
    private List<event> eventsUser;

    public eventxuser() {
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public List<event> getEventsUser() {
        return eventsUser;
    }

    public void setEventsUser(List<event> eventsUser) {
        this.eventsUser = eventsUser;
    }
}
