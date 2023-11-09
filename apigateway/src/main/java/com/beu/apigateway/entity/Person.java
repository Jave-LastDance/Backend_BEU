package com.beu.apigateway.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
    @Id
    private Integer documento;
    private String usuario;
    private String nombre;
    private String apellidos;
    private String emplid;
    private String tipodocumento;
    private String oprid;
    private Integer diasvencimiento;
    private String programa;
    private String dependencia;



    private String token;

    // Getters y setters

    public Integer getDocumento() {
        return documento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmplid() {
        return emplid;
    }

    public void setEmplid(String emplId) {
        this.emplid = emplId;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipoDocumento) {
        this.tipodocumento = tipoDocumento;
    }

    public String getOprid() {
        return oprid;
    }

    public void setOprid(String oprId) {
        this.oprid = oprId;
    }

    public Integer getDiasvencimiento() {
        return diasvencimiento;
    }

    public void setDiasvencimiento(Integer diasVencimiento) {
        this.diasvencimiento = diasVencimiento;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
