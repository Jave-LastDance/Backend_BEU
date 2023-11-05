package com.example.personalizationmicroservice.entity;

public class user {

    private Integer id;
    private String name;
    private String surname;
    private String rol;
    private String program;

    private String email;

    public user() {
    }

    public user(Integer id, String name, String surname, String rol, String program, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.rol = rol;
        this.program = program;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
