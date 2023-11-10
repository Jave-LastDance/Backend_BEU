package com.example.eventmicroservice.entity;

import java.util.Date;

public class user {

    int id;
    String username;
    String lastname;
    String firstname;
    String password;
    Role role;
    private boolean seccionIniciada;
    private String programa;
    private String rol;
    private String photo;

    public user(int id, String username, String lastname, String firstname, String password, Role role, boolean seccionIniciada, String programa, String rol, String photo, String tokenMovil, Date fechaNacimiento) {
        this.id = id;
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
        this.role = role;
        this.seccionIniciada = seccionIniciada;
        this.programa = programa;
        this.rol = rol;
        this.photo = photo;
        this.tokenMovil = tokenMovil;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isSeccionIniciada() {
        return seccionIniciada;
    }

    public void setSeccionIniciada(boolean seccionIniciada) {
        this.seccionIniciada = seccionIniciada;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTokenMovil() {
        return tokenMovil;
    }

    public void setTokenMovil(String tokenMovil) {
        this.tokenMovil = tokenMovil;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    private String tokenMovil;
    private Date fechaNacimiento;


    public user() {
    }


}
