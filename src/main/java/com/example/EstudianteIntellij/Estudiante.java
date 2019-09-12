package com.example.EstudianteIntellij;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Estudiante
{
    private @Id @GeneratedValue Long id;
    private String nombre;
    private String apellido;
    private  String nota;

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido, String nota) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nota = nota;
    }

    public String getNota() {
        return nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
