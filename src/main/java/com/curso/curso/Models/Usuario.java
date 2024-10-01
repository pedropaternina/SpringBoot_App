package com.curso.curso.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity()
@Table(name = "usuarios")
public class Usuario {

    @Getter @Setter @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Getter @Setter @Column(name = "nombres")
    private String nombres;
    @Getter @Setter @Column(name = "apellidos")
    private String apellidos;
    @Getter @Setter @Column(name = "email")
    private String email;
    @Getter @Setter @Column(name = "telefono")
    private String telefono;
    @Getter @Setter @Column(name = "password")
    private String password;




}
