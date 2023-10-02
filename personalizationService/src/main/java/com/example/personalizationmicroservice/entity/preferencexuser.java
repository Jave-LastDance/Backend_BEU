package com.example.personalizationmicroservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "preferenceuser")
public class preferencexuser {

    @Id
    @Column(name = "id_preference_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_preference_user;

    @Column(name = "id_user")
    private Integer id_user;

    @Column(name = "preferenceid_preference")
    private Integer preferenceid_preference;

    public preferencexuser() {
    }

    public preferencexuser(Integer id_preference_user, Integer id_user, Integer preferenceid_preference) {
        this.id_preference_user = id_preference_user;
        this.id_user = id_user;
        this.preferenceid_preference = preferenceid_preference;
    }

    public Integer getId_preference_user() {
        return id_preference_user;
    }

    public void setId_preference_user(Integer id_preference_user) {
        this.id_preference_user = id_preference_user;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getPreferenceid_preference() {
        return preferenceid_preference;
    }

    public void setPreferenceid_preference(Integer preferenceid_preference) {
        this.preferenceid_preference = preferenceid_preference;
    }
}
