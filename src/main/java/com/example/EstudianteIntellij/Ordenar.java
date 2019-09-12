package com.example.EstudianteIntellij;

import jdk.internal.org.jline.utils.Status;
import lombok.Data;
import org.hibernate.criterion.Order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "CUSTOMER_ORDER")
public class Ordenar{

    private @Id @GeneratedValue Long id;
    private String descripcion;
    private String status;

    Ordenar(){

    }

    Ordenar(String descripcion, String status){
        this.descripcion=descripcion;
        this.status=status;
    }



}
