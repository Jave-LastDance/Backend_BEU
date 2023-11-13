package com.irojas.demojwt.User;

import jakarta.persistence.*;

@Entity
@Table(name = "eventosxpersona")
public class EventosxPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_evento", nullable = false)
    private Long idEvento;

    @Column(name = "id_persona")
    private Long idPersona;

    // Constructor, getters, setters, etc.

    public EventosxPersona() {
    }

    public EventosxPersona(Long idEvento, Long idPersona) {
        this.idEvento = idEvento;
        this.idPersona = idPersona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

}
