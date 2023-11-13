package com.beu.apigateway.entity;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User {
    @Id
    @GeneratedValue
    Integer id;
    @Basic
    @Column(nullable = false,unique = true)
    String username;
    @Column(nullable = false)
    String lastname;
    String firstname;

    String password;
    @Enumerated(EnumType.STRING)
    Role role;
    @Column(columnDefinition = "TINYINT(1)")
    private boolean seccionIniciada;

    private String programa;
    private String rol;
    private String photo;

    private String tokenMovil;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

}
