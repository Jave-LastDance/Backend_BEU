package com.example.eventmicroservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "center")
public class center {
    @Id
    @Column(name="id_unity")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUnity;

    @Column(name="unity_name")
    private String unityName;

    public center() {
    }

    public center(Integer idUnity, String unityName) {
        this.idUnity = idUnity;
        this.unityName = unityName;
    }

    public Integer getIdUnity() {
        return idUnity;
    }

    public void setIdUnity(Integer idUnity) {
        this.idUnity = idUnity;
    }

    public String getUnityName() {
        return unityName;
    }

    public void setUnityName(String unityName) {
        this.unityName = unityName;
    }
}



