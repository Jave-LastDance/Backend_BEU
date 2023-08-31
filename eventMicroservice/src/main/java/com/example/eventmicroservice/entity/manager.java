package com.example.eventmicroservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "manager")
public class manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Integer idAdmin;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "Centerid_unity")
    private Integer CenteridUnity;

    public manager() {
    }

    public manager(Integer idAdmin, Integer idUser, Integer centeridUnity) {
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        CenteridUnity = centeridUnity;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getCenteridUnity() {
        return CenteridUnity;
    }

    public void setCenteridUnity(Integer centeridUnity) {
        CenteridUnity = centeridUnity;
    }
}
