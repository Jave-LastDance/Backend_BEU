package com.example.eventmicroservice.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "head")
public class head {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_head")
    private Integer idHead;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "Centerid_unity")
    private Integer CenteridUnity;

    public head() {
    }

    public head(Integer idHead, Integer idUser, Integer centeridUnity) {
        this.idHead = idHead;
        this.idUser = idUser;
        CenteridUnity = centeridUnity;
    }

    public Integer getIdHead() {
        return idHead;
    }

    public void setIdHead(Integer idHead) {
        this.idHead = idHead;
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
