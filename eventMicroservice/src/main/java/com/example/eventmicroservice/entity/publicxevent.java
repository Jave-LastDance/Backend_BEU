package com.example.eventmicroservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "publicxevent")
public class publicxevent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_public_event")
    private Integer idPublicXEvent;

    @Column(name = "Eventid_event")
    private Integer EventidEvent;

    @Column(name="Publicid_public")
    private Integer PublicidPublic;

    public publicxevent() {
    }

    public publicxevent(Integer idPublicXEvent, Integer eventidEvent, Integer publicidPublic) {
        this.idPublicXEvent = idPublicXEvent;
        EventidEvent = eventidEvent;
        PublicidPublic = publicidPublic;
    }

    public Integer getEventidEvent() {
        return EventidEvent;
    }

    public void setEventidEvent(Integer eventidEvent) {
        EventidEvent = eventidEvent;
    }

    public Integer getPublicidPublic() {
        return PublicidPublic;
    }

    public void setPublicidPublic(Integer publicidPublic) {
        PublicidPublic = publicidPublic;
    }
}
