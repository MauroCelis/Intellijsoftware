package com.example.EstudianteIntellij;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity

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


    public String getNombre() {
        return this.nombre+" "+this.apellido;
    }


    public void setNombre(String nombre) {
        String [] parts=nombre.split(" " );
        this.nombre=parts[0];
        this.apellido=parts[1];

    }

}
