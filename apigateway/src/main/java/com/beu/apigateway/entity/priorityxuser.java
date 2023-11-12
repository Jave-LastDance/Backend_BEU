package com.beu.apigateway.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "priorityuser")
public class priorityxuser {

    @Id
    @Column(name = "id_priority")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_priority;

    @Column(name = "id_user")
    private Integer id_user;

    @Column(name = "importance")
    private Integer importance;

    @Column(name = "priority_rule")
    private String priorityrule;

    public priorityxuser() {
    }

    public priorityxuser(Integer id_priority, Integer id_user, Integer importance, String priorityrule) {
        this.id_priority = id_priority;
        this.id_user = id_user;
        this.importance = importance;
        this.priorityrule = priorityrule;
    }

    public Integer getId_priority() {
        return id_priority;
    }

    public void setId_priority(Integer id_priority) {
        this.id_priority = id_priority;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public String getPriorityrule() {
        return priorityrule;
    }

    public void setPriorityrule(String priorityrule) {
        this.priorityrule = priorityrule;
    }
}
